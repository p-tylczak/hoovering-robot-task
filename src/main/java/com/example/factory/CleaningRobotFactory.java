package com.example.factory;

import com.example.exception.InvalidInputException;
import com.example.model.RoomGrid;
import com.example.service.CleaningRobot;
import com.example.service.RoomGridInitializer;
import com.example.service.RoomGridValidator;
import org.springframework.stereotype.Component;

@Component
public class CleaningRobotFactory {

    private final RoomGridValidator roomGridValidator;
    private final RoomGridInitializer roomGridInitializer;

    public CleaningRobotFactory(RoomGridValidator roomGridValidator,
                                RoomGridInitializer roomGridInitializer) {
        this.roomGridValidator = roomGridValidator;
        this.roomGridInitializer = roomGridInitializer;
    }

    public CleaningRobot create(RoomGrid roomGrid) {
        if (!roomGridValidator.isValid(roomGrid)) {
            throw new InvalidInputException("roomGrid is invalid.");
        }

        final int width = roomGrid.getWidth();
        final int height = roomGrid.getHeight();

        final var grid = roomGridInitializer.initialize(width, height, roomGrid.getLocationOfDirtPatches());

        return new CleaningRobot(grid);
    }
}
