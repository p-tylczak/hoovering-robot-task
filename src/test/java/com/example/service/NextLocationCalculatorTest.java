package com.example.service;

import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NextLocationCalculatorTest {

    private static final GridLocation ZERO_LOCATION = new GridLocation(0, 0);
    private NextLocationCalculator nextLocationCalculator;

    @BeforeEach
    void setUp() {
        nextLocationCalculator = new NextLocationCalculator();
    }

    @Test
    void calculate_whenLocationIsNull_shouldThrowInvalidInputException() {
        assertThatThrownBy(() -> nextLocationCalculator.calculate(null, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("gridLocation must be provided.");
    }

    @Test
    void calculate_whenLocationIsProvidedAndDirectionIsNull_shouldThrowInvalidInputException() {
        assertThatThrownBy(() -> nextLocationCalculator.calculate(ZERO_LOCATION, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("direction must be provided.");
    }

    @Test
    void calculate_whenNavigatingNorthFromZeroZero_shouldReturnLocationOfZeroOne() {
        final GridLocation result = nextLocationCalculator.calculate(ZERO_LOCATION, Direction.NORTH);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isOne();
    }

    @Test
    void calculate_whenNavigatingEastFromZeroZero_shouldReturnLocationOfOneZero() {
        final GridLocation result = nextLocationCalculator.calculate(ZERO_LOCATION, Direction.EAST);

        assertThat(result.getXCoordinate()).isOne();
        assertThat(result.getYCoordinate()).isZero();
    }

    @Test
    void calculate_whenNavigatingSouthFromZeroZero_shouldReturnLocationOfZeroMinusOne() {
        final GridLocation result = nextLocationCalculator.calculate(ZERO_LOCATION, Direction.SOUTH);

        assertThat(result.getXCoordinate()).isZero();
        assertThat(result.getYCoordinate()).isEqualTo(-1);
    }

    @Test
    void calculate_whenNavigatingWestFromZeroZero_shouldReturnLocationOfMinusOneZero() {
        final GridLocation result = nextLocationCalculator.calculate(ZERO_LOCATION, Direction.WEST);

        assertThat(result.getXCoordinate()).isEqualTo(-1);
        assertThat(result.getYCoordinate()).isZero();
    }
}
