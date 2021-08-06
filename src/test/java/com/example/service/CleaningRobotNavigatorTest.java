package com.example.service;

import com.example.model.GridLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CleaningRobotNavigatorTest {

    private final NextLocationCalculator nextLocationCalculator = new NextLocationCalculator();
    private CleaningRobotNavigator cleaningRobotNavigator;

    @BeforeEach
    void setUp() {
        cleaningRobotNavigator = new CleaningRobotNavigator(nextLocationCalculator);
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingSouth_shouldStayAtZeroZero() {
        final var direction = Direction.SOUTH;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 1, 1);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingWest_shouldStayAtZeroZero() {
        final var direction = Direction.WEST;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 1, 1);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingNorthAndGridHeightIs1_shouldStayAtZeroZero() {
        final var direction = Direction.NORTH;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 1, 1);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingEastAndGridWidthIs1_shouldStayAtZeroZero() {
        final var direction = Direction.EAST;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 1, 1);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingNorthAndGridHeightIs2_shouldReturnNewLocationAtZeroAndOne() {
        final var direction = Direction.NORTH;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 1, 2);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isOne();
    }

    @Test
    void navigate_whenCurrentLocationIsZeroZeroAndNavigatingEastAndGridWidthIs2_shouldReturnNewLocationAtOneAndZero() {
        final var direction = Direction.EAST;
        final var currentLocation = new GridLocation(0, 0);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 2, 2);

        assertThat(result.getXCoordinate()).isOne();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsOneOneAndNavigatingSouthAndGridHeightIs2_shouldReturnNewLocationAtOneAndZero() {
        final var direction = Direction.SOUTH;
        final var currentLocation = new GridLocation(1, 1);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 2, 2);

        assertThat(result.getXCoordinate()).isOne();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void navigate_whenCurrentLocationIsOneOneAndNavigatingWestAndGridWidthIs2_shouldReturnNewLocationAtZeroAndOne() {
        final var direction = Direction.WEST;
        final var currentLocation = new GridLocation(1, 1);

        final var result = cleaningRobotNavigator.navigate(currentLocation, direction, 2, 2);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isOne();
    }
}
