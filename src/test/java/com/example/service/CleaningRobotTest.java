package com.example.service;

import com.example.model.CleanPatch;
import com.example.model.DirtPatch;
import com.example.model.GridLocation;
import com.example.model.Patch;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CleaningRobotTest {

    @Test
    void cleanLocation_whenHasDirtPatchAtZeroZeroAndCleanLocationIsZeroZero_shouldCleanThatPatch() {
        final var grid = new Patch[][]{{new DirtPatch()}};
        final var cleaningRobot = new CleaningRobot(grid);

        assertThat(grid[0][0]).isInstanceOf(DirtPatch.class);

        cleaningRobot.cleanLocation(new GridLocation(0, 0));

        assertThat(cleaningRobot.getCleanedPatchCount()).isOne();
        assertThat(grid[0][0]).isInstanceOf(CleanPatch.class);
    }

    @Test
    void cleanLocation_whenHasCleanPatchAtZeroZeroAndCleanLocationIsZeroZero_shouldNotCleanThatPatch() {
        final var grid = new Patch[][]{{new CleanPatch()}};
        final var cleaningRobot = new CleaningRobot(grid);

        assertThat(grid[0][0]).isInstanceOf(CleanPatch.class);

        cleaningRobot.cleanLocation(new GridLocation(0, 0));

        assertThat(cleaningRobot.getCleanedPatchCount()).isZero();
        assertThat(grid[0][0]).isInstanceOf(CleanPatch.class);
    }
}