package com.example.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DirectionTest {

    @Test
    void from_whenAliasIsN_shouldReturnOptionalOfNorth() {
        final var result = Direction.from('N');
        assertThat(result).contains(Direction.NORTH);
    }

    @Test
    void from_whenAliasIsE_shouldReturnOptionalOfEast() {
        final var result = Direction.from('E');
        assertThat(result).contains(Direction.EAST);
    }

    @Test
    void from_whenAliasIsS_shouldReturnOptionalOfSouth() {
        final var result = Direction.from('S');
        assertThat(result).contains(Direction.SOUTH);
    }

    @Test
    void from_whenAliasIsW_shouldReturnOptionalOfWest() {
        final var result = Direction.from('W');
        assertThat(result).contains(Direction.WEST);
    }

    @Test
    void from_whenAliasIsX_shouldReturnEmpty() {
        final var result = Direction.from('X');
        assertThat(result).isEmpty();
    }

    @Test
    void getXOffset_whenNorth_shouldReturn0() {
        final var result = Direction.NORTH;
        assertThat(result.getXOffset()).isZero();
    }

    @Test
    void getYOffset_whenNorth_shouldReturn1() {
        final var result = Direction.NORTH;
        assertThat(result.getYOffset()).isOne();
    }

    @Test
    void getXOffset_whenEast_shouldReturn1() {
        final var result = Direction.EAST;
        assertThat(result.getXOffset()).isOne();
    }

    @Test
    void getYOffset_whenEast_shouldReturn0() {
        final var result = Direction.EAST;
        assertThat(result.getYOffset()).isZero();
    }

    @Test
    void getXOffset_whenSouth_shouldReturn0() {
        final var result = Direction.SOUTH;
        assertThat(result.getXOffset()).isZero();
    }

    @Test
    void getYOffset_whenSouth_shouldReturnMinus1() {
        final var result = Direction.SOUTH;
        assertThat(result.getYOffset()).isEqualTo(-1);
    }

    @Test
    void getXOffset_whenWest_shouldReturnMinus1() {
        final var result = Direction.WEST;
        assertThat(result.getXOffset()).isEqualTo(-1);
    }

    @Test
    void getYOffset_whenWest_shouldReturn0() {
        final var result = Direction.WEST;
        assertThat(result.getYOffset()).isZero();
    }
}
