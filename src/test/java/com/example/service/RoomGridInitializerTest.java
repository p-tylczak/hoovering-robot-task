package com.example.service;

import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import com.example.model.DirtPatch;
import com.example.model.CleanPatch;
import com.example.service.RoomGridInitializer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RoomGridInitializerTest {

    private RoomGridInitializer roomGridInitializer;

    @BeforeEach
    void setUp() {
        this.roomGridInitializer = new RoomGridInitializer();
    }

    @Test
    void initialize_whenWidthIsZero_shouldThrowException() {
        assertThatThrownBy(() -> roomGridInitializer.initialize(0, 0, Collections.emptyList()))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("width must be positive.");
    }

    @Test
    void initialize_whenWidthIsOneAndHeightIs0_shouldThrowException() {
        assertThatThrownBy(() -> roomGridInitializer.initialize(1, 0, Collections.emptyList()))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("height must be positive.");
    }

    @Test
    void initialize_whenWidthIs3AndHeightIs3AndDirtPatchesIsEmpty_shouldHave9CleanPatches() {
        final var result = roomGridInitializer.initialize(3, 3, Collections.emptyList());

        assertThat(result[0][0]).isInstanceOf(CleanPatch.class);
        assertThat(result[0][1]).isInstanceOf(CleanPatch.class);
        assertThat(result[0][2]).isInstanceOf(CleanPatch.class);

        assertThat(result[1][0]).isInstanceOf(CleanPatch.class);
        assertThat(result[1][1]).isInstanceOf(CleanPatch.class);
        assertThat(result[1][2]).isInstanceOf(CleanPatch.class);

        assertThat(result[2][0]).isInstanceOf(CleanPatch.class);
        assertThat(result[2][1]).isInstanceOf(CleanPatch.class);
        assertThat(result[2][2]).isInstanceOf(CleanPatch.class);
    }

    @Test
    void initialize_whenWidthIs3AndHeightIs3AndHas3DirtPatchesProvided_shouldHave6CleanPatchesAnd3DirtPatches() {
        var dirtPatches = Arrays.asList(
                new GridLocation(0, 0),
                new GridLocation(1, 1),
                new GridLocation(2, 2));
        final var result = roomGridInitializer.initialize(3, 3, dirtPatches);

        assertThat(result[0][0]).isInstanceOf(DirtPatch.class);
        assertThat(result[0][1]).isInstanceOf(CleanPatch.class);
        assertThat(result[0][2]).isInstanceOf(CleanPatch.class);

        assertThat(result[1][0]).isInstanceOf(CleanPatch.class);
        assertThat(result[1][1]).isInstanceOf(DirtPatch.class);
        assertThat(result[1][2]).isInstanceOf(CleanPatch.class);

        assertThat(result[2][0]).isInstanceOf(CleanPatch.class);
        assertThat(result[2][1]).isInstanceOf(CleanPatch.class);
        assertThat(result[2][2]).isInstanceOf(DirtPatch.class);
    }
}