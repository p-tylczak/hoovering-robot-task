package com.example.service;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    NORTH(0, 1, 'N'),
    EAST(1, 0, 'E'),
    SOUTH(0, -1, 'S'),
    WEST(-1, 0, 'W');

    private final int xOffset;
    private final int yOffset;
    private final char alias;

    Direction(int xOffset, int yOffset, char alias) {
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.alias = alias;
    }

    public int getXOffset() {
        return xOffset;
    }

    public int getYOffset() {
        return yOffset;
    }

    public static Optional<Direction> from(char alias) {
        return Arrays.stream(Direction.values())
                .filter(d -> d.alias == alias)
                .findFirst();
    }
}
