package com.example;

import com.example.converter.ArrayToGridLocationConverter;
import com.example.converter.StringToDirectionConverter;
import com.example.factory.RoomGridFactory;
import com.example.model.GridLocation;
import com.example.resource.CleaningRobotNavigationResource;
import com.example.resource.CleaningRobotResultResource;
import com.example.service.CleaningRobotService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class Controller {

    private final CleaningRobotService cleaningRobotService;
    private final RoomGridFactory roomGridFactory;
    private final ArrayToGridLocationConverter arrayToGridLocationConverter;
    private final StringToDirectionConverter stringToDirectionConverter;

    public Controller(CleaningRobotService cleaningRobotService,
                      RoomGridFactory roomGridFactory,
                      ArrayToGridLocationConverter arrayToGridLocationConverter,
                      StringToDirectionConverter stringToDirectionConverter) {
        this.cleaningRobotService = cleaningRobotService;
        this.roomGridFactory = roomGridFactory;
        this.arrayToGridLocationConverter = arrayToGridLocationConverter;
        this.stringToDirectionConverter = stringToDirectionConverter;
    }

    @PostMapping("/instructions")
    public CleaningRobotResultResource processInstructions(@Valid @RequestBody CleaningRobotNavigationResource resource) {
        final var roomGrid = roomGridFactory.create(resource.getRoomSize(), resource.getDirtPatches());
        final var initialLocation = arrayToGridLocationConverter.convert(resource.getStartingPosition());
        final var instructions = stringToDirectionConverter.convert(resource.getNavigationInstructions());

        final var robotCleaningResult = cleaningRobotService.startCleaning(
                roomGrid, initialLocation, instructions);

        return new CleaningRobotResultResource(
                toArray(robotCleaningResult.getFinalPosition()),
                robotCleaningResult.getDirtPatchesCleaned());
    }

    private int[] toArray(GridLocation location) {
        return new int[]{location.getXCoordinate(), location.getYCoordinate()};
    }
}
