package com.example.service;

import com.example.model.GridLocation;
import com.example.model.RoomGrid;
import com.example.service.RoomGridValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class RoomGridValidatorTest {

    private RoomGridValidator roomGridValidator;

    @BeforeEach
    void setUp() {
        this.roomGridValidator = new RoomGridValidator();
    }

    @Test
    void isValid_whenDirtPatchXCoordinateIs1AndRoomGridWidthIs1_shouldReturnFalse() {
        final var dirtPatch = new GridLocation(1, 0);
        final var roomGrid = new RoomGrid(1, 1, Collections.singletonList(dirtPatch));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isFalse();
    }

    @Test
    void isValid_whenDirtPatchYCoordinateIs1AndRoomGridHeightIs1_shouldReturnFalse() {
        final var dirtPatch = new GridLocation(0, 1);
        final var roomGrid = new RoomGrid(1, 1, Collections.singletonList(dirtPatch));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isFalse();
    }

    @Test
    void isValid_whenDirtPatchXCoordinateIsMinus1AndRoomGridWidthIs1_shouldReturnFalse() {
        final var dirtPatch = new GridLocation(-1, 0);
        final var roomGrid = new RoomGrid(1, 1, Collections.singletonList(dirtPatch));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isFalse();
    }

    @Test
    void isValid_whenDirtPatchYCoordinateIsMinus1AndRoomGridHeightIs1_shouldReturnFalse() {
        final var dirtPatch = new GridLocation(0, -1);
        final var roomGrid = new RoomGrid(1, 1, Collections.singletonList(dirtPatch));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isFalse();
    }

    @Test
    void isValid_whenDirtPatchXAndYCoordinatesAre0AndRoomGridSizeIs1_shouldReturnTrue() {
        final var dirtPatch = new GridLocation(0, 0);
        final var roomGrid = new RoomGrid(1, 1, Collections.singletonList(dirtPatch));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isTrue();
    }

    @Test
    void isValid_whenTwoDirtPatchesAreOnTheRoomGridAndOneDirtPatchIsOutsideOfRoomGrid_shouldReturnFalse() {
        final var dirtPatchOn1 = new GridLocation(0, 0);
        final var dirtPatchOn2 = new GridLocation(1, 1);
        final var dirtPatchOut = new GridLocation(2, 2);
        final var roomGrid = new RoomGrid(2, 2, Arrays.asList(dirtPatchOn1, dirtPatchOn2, dirtPatchOut));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isFalse();
    }

    @Test
    void isValid_whenAllDirtPatchesAreOnTheRoomGrid_shouldReturnTrue() {
        final var dirtPatchOn1 = new GridLocation(0, 0);
        final var dirtPatchOn2 = new GridLocation(0, 1);
        final var dirtPatchOn3 = new GridLocation(1, 0);
        final var dirtPatchOn4 = new GridLocation(1, 1);

        final var roomGrid = new RoomGrid(2, 2, Arrays.asList(dirtPatchOn1, dirtPatchOn2, dirtPatchOn3, dirtPatchOn4));

        final var result = roomGridValidator.isValid(roomGrid);

        assertThat(result).isTrue();
    }
}