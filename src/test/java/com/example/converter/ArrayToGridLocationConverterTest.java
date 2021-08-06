package com.example.converter;

import com.example.model.GridLocation;
import com.example.exception.InvalidInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ArrayToGridLocationConverterTest {

    private ArrayToGridLocationConverter arrayToGridLocationConverter;

    @BeforeEach
    void setUp() {
        this.arrayToGridLocationConverter = new ArrayToGridLocationConverter();
    }

    @Test
    void convert_whenLocationIsNull_shouldThrowException() {
        assertThatThrownBy(() -> arrayToGridLocationConverter.convert(null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("location must have exactly two elements.");
    }

    @Test
    void convert_whenLocationIsEmptyArray_shouldThrowException() {
        assertThatThrownBy(() -> arrayToGridLocationConverter.convert(new int[]{}))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("location must have exactly two elements.");
    }

    @Test
    void convert_whenLocationHasOneElement_shouldThrowException() {
        assertThatThrownBy(() -> arrayToGridLocationConverter.convert(new int[]{1}))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("location must have exactly two elements.");
    }

    @Test
    void convert_whenLocationHasThreeElements_shouldThrowException() {
        assertThatThrownBy(() -> arrayToGridLocationConverter.convert(new int[]{1, 2, 3}))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("location must have exactly two elements.");
    }

    @Test
    void convert_whenLocationHasOneAndTwo_shouldReturnLocationWithX1AndY2() {
        final var result = arrayToGridLocationConverter.convert(new int[]{1, 2});
        assertThat(result).isEqualTo(new GridLocation(1, 2));
    }
}