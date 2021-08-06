package com.example.model;

import java.util.Objects;

public class GridLocation {

    private final int xCoordinate;
    private final int yCoordinate;

    public GridLocation(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public int getXCoordinate() {
        return xCoordinate;
    }

    public int getYCoordinate() {
        return yCoordinate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GridLocation that = (GridLocation) o;
        return xCoordinate == that.xCoordinate && yCoordinate == that.yCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinate, yCoordinate);
    }
}
