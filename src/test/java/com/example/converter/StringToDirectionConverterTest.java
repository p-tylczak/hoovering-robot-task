package com.example.converter;

import com.example.service.Direction;
import com.example.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StringToDirectionConverterTest {

    private StringToDirectionConverter stringToDirectionConverter;

    @BeforeEach
    void setUp() {
        this.stringToDirectionConverter = new StringToDirectionConverter();
    }

    @Test
    void convert_whenInstructionsIsNull_shouldThrowException() {
        assertThatThrownBy(() -> stringToDirectionConverter.convert(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("instructions must be provided.");
    }

    @Test
    void convert_whenInstructionsIsEmpty_shouldThrowException() {
        assertThatThrownBy(() -> stringToDirectionConverter.convert(""))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("instructions must be provided.");
    }

    @Test
    void convert_whenInstructionsIsWhitespaceOnly_shouldThrowException() {
        assertThatThrownBy(() -> stringToDirectionConverter.convert(" "))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("instructions must be provided.");
    }

    @Test
    void convert_whenInstructionsIsX_shouldThrowException() {
        assertThatThrownBy(() -> stringToDirectionConverter.convert("X"))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("unable to convert 'X' to direction.");
    }

    @Test
    void convert_whenInstructionsIsNESW_shouldReturnNorthEastSouthWestDirections() {
        final var result = stringToDirectionConverter.convert("NESW");
        assertThat(result).containsExactly(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST);
    }

    @Test
    void convert_whenInstructionsIsNNNEEEWWWSSS_shouldReturnCorrectDirections() {
        final var result = stringToDirectionConverter.convert("NNNEEEWWWSSS");
        assertThat(result).containsExactly(
                Direction.NORTH,
                Direction.NORTH,
                Direction.NORTH,
                Direction.EAST,
                Direction.EAST,
                Direction.EAST,
                Direction.WEST,
                Direction.WEST,
                Direction.WEST,
                Direction.SOUTH,
                Direction.SOUTH,
                Direction.SOUTH);
    }
}