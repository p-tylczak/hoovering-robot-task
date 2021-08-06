package com.example.service;

import com.example.model.CleanPatch;
import com.example.model.DirtPatch;
import com.example.model.GridLocation;
import com.example.model.Patch;

public class CleaningRobot {

    private final Patch[][] grid;
    private int cleanedPatchCount = 0;

    public CleaningRobot(Patch[][] grid) {
        this.grid = grid;
    }

    public void cleanLocation(GridLocation location) {
        final int x = location.getXCoordinate();
        final int y = location.getYCoordinate();

        final boolean needsCleaning = grid[x][y] instanceof DirtPatch;

        if (needsCleaning) {
            grid[x][y] = new CleanPatch();
            cleanedPatchCount++;
        }
    }

    public int getCleanedPatchCount() {
        return cleanedPatchCount;
    }
}
