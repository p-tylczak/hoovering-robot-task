package com.example.service;

import com.example.factory.CleaningRobotFactory;
import com.example.model.GridLocation;
import com.example.model.RobotCleaningResult;
import com.example.model.RoomGrid;
import com.example.repository.RobotCleaningResultEntity;
import com.example.repository.RobotCleaningResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CleaningRobotService {

    private final CleaningRobotNavigator cleaningRobotNavigator;
    private final CleaningRobotFactory cleaningRobotFactory;
    private final RobotCleaningResultRepository robotCleaningResultRepository;

    public CleaningRobotService(CleaningRobotNavigator cleaningRobotNavigator,
                                CleaningRobotFactory cleaningRobotFactory,
                                RobotCleaningResultRepository robotCleaningResultRepository) {
        this.cleaningRobotNavigator = cleaningRobotNavigator;
        this.cleaningRobotFactory = cleaningRobotFactory;
        this.robotCleaningResultRepository = robotCleaningResultRepository;
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

        final var result = new RobotCleaningResult(
                cleaningRobotLocation,
                cleaningRobot.getCleanedPatchCount());

        saveResult(result);

        return result;
    }

    private void saveResult(RobotCleaningResult result) {
        robotCleaningResultRepository.save(
                new RobotCleaningResultEntity(
                        result.getFinalPosition().getXCoordinate(),
                        result.getFinalPosition().getYCoordinate(),
                        result.getDirtPatchesCleaned()));
    }
}
