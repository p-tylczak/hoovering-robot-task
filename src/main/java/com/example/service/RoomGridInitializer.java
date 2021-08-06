package com.example.service;

import com.example.exception.InvalidInputException;
import com.example.model.CleanPatch;
import com.example.model.DirtPatch;
import com.example.model.GridLocation;
import com.example.model.Patch;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoomGridInitializer {

    public Patch[][] initialize(int width, int height, List<GridLocation> locationOfDirtPatches) {
        if (width <= 0) {
            throw new InvalidInputException("width must be positive.");
        }

        if (height <= 0) {
            throw new InvalidInputException("height must be positive.");
        }

        final var grid = new Patch[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final var gridLocation = new GridLocation(x, y);

                final var patch = locationOfDirtPatches.contains(gridLocation)
                        ? new DirtPatch()
                        : new CleanPatch();

                grid[x][y] = patch;
            }
        }

        return grid;
    }
}
