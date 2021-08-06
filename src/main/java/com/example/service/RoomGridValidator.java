package com.example.service;

import com.example.model.GridLocation;
import com.example.model.RoomGrid;
import org.springframework.stereotype.Component;

@Component
public class RoomGridValidator {

    public boolean isValid(RoomGrid roomGrid) {
        int width = roomGrid.getWidth();
        int height = roomGrid.getHeight();

        boolean isValidSoFar;

        for (final GridLocation dirtLocation : roomGrid.getLocationOfDirtPatches()) {
            isValidSoFar = dirtLocation.getXCoordinate() < width && dirtLocation.getYCoordinate() < height
                    && dirtLocation.getXCoordinate() >= 0 && dirtLocation.getYCoordinate() >= 0;

            if (!isValidSoFar) {
                return false;
            }
        }

        return true;
    }
}
