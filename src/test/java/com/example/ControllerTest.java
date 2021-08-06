package com.example;

import com.example.converter.ArrayToGridLocationConverter;
import com.example.converter.StringToDirectionConverter;
import com.example.factory.RoomGridFactory;
import com.example.model.GridLocation;
import com.example.model.RobotCleaningResult;
import com.example.resource.CleaningRobotNavigationResource;
import com.example.service.CleaningRobotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    private CleaningRobotService cleaningRobotService;
    @Mock
    private RoomGridFactory roomGridFactory;
    @Mock
    private ArrayToGridLocationConverter arrayToGridLocationConverter;
    @Mock
    private StringToDirectionConverter stringToDirectionConverter;

    private Controller controller;

    @BeforeEach
    void setUp() {
        this.controller = new Controller(
                cleaningRobotService,
                roomGridFactory,
                arrayToGridLocationConverter,
                stringToDirectionConverter);
    }

    @Test
    void processInstructions_shouldCallRelevantServices() {
        final var roomSize = new int[]{5, 5};
        final var dirtPatches = new int[][]{{0, 0}};
        final var initialLocation = new int[]{0, 0};
        final var instructions = "NESW";
        final var cleaningRobotNavigationResource = new CleaningRobotNavigationResource(
                roomSize,
                initialLocation,
                dirtPatches,
                instructions);
        final var cleaningResult = new RobotCleaningResult(new GridLocation(0, 0), 1);
        when(cleaningRobotService.startCleaning(any(), any(), anyList())).thenReturn(cleaningResult);

        final var result = controller.processInstructions(cleaningRobotNavigationResource);

        assertThat(result.getFinalPosition()).containsExactly(0, 0);
        assertThat(result.getDirtPatchesCleaned()).isOne();
        verify(roomGridFactory).create(roomSize, dirtPatches);
        verify(arrayToGridLocationConverter).convert(initialLocation);
        verify(stringToDirectionConverter).convert(instructions);
    }
}
