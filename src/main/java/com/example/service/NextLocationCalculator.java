package com.example.service;

import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import org.springframework.stereotype.Component;

@Component
public class NextLocationCalculator {

    public GridLocation calculate(GridLocation gridLocation, Direction direction) {
        if (gridLocation == null)
            throw new InvalidInputException("gridLocation must be provided.");

        if (direction == null)
            throw new InvalidInputException("direction must be provided.");

        return new GridLocation(
                gridLocation.getXCoordinate() + direction.getXOffset(),
                gridLocation.getYCoordinate() + direction.getYOffset());
    }
}
