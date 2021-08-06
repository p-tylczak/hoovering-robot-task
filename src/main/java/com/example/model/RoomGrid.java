package com.example.model;

import java.util.List;

public class RoomGrid {

    private final int width;
    private final int height;
    private final List<GridLocation> locationOfDirtPatches;

    public RoomGrid(int width, int height, List<GridLocation> locationOfDirtPatches) {
        this.width = width;
        this.height = height;
        this.locationOfDirtPatches = locationOfDirtPatches;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<GridLocation> getLocationOfDirtPatches() {
        return locationOfDirtPatches;
    }
}
