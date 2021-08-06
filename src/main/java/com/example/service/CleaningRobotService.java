package com.example.service;

import com.example.factory.CleaningRobotFactory;
import com.example.model.GridLocation;
import com.example.model.RobotCleaningResult;
import com.example.model.RoomGrid;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CleaningRobotService {

    private final CleaningRobotNavigator cleaningRobotNavigator;
    private final CleaningRobotFactory cleaningRobotFactory;

    public CleaningRobotService(CleaningRobotNavigator cleaningRobotNavigator,
                                CleaningRobotFactory cleaningRobotFactory) {
        this.cleaningRobotNavigator = cleaningRobotNavigator;
        this.cleaningRobotFactory = cleaningRobotFactory;
    }

    public RobotCleaningResult startCleaning(RoomGrid roomGrid,
                                             GridLocation initialCleaningRobotLocation,
                                             List<Direction> navigationInstructions) {
        var cleaningRobotLocation = initialCleaningRobotLocation;

        final var cleaningRobot = cleaningRobotFactory.create(roomGrid);
        cleaningRobot.cleanLocation(cleaningRobotLocation);

        for (final var direction : navigationInstructions) {
            cleaningRobotLocation = cleaningRobotNavigator.navigate(
                    cleaningRobotLocation,
                    direction,
                    roomGrid.getWidth(),
                    roomGrid.getHeight());

            cleaningRobot.cleanLocation(cleaningRobotLocation);
        }

        return new RobotCleaningResult(
                cleaningRobotLocation,
                cleaningRobot.getCleanedPatchCount());
    }
}
