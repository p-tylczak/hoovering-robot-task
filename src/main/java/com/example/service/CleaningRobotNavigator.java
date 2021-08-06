package com.example.service;

import com.example.model.GridLocation;
import org.springframework.stereotype.Component;

@Component
public class CleaningRobotNavigator {

    private final NextLocationCalculator nextLocationCalculator;

    public CleaningRobotNavigator(NextLocationCalculator nextLocationCalculator) {
        this.nextLocationCalculator = nextLocationCalculator;
    }

    public GridLocation navigate(GridLocation currentLocation, Direction direction, int gridWidth, int gridHeight) {
        final var nextLocation = nextLocationCalculator.calculate(currentLocation, direction);

        final boolean hasHitWall = nextLocation.getXCoordinate() < 0
                || nextLocation.getXCoordinate() >= gridWidth
                || nextLocation.getYCoordinate() < 0
                || nextLocation.getYCoordinate() >= gridHeight;

        return hasHitWall
                ? currentLocation
                : nextLocation;
    }
}
