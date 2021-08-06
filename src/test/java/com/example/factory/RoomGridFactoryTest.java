package com.example.factory;

import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import com.example.converter.ArrayToGridLocationConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoomGridFactoryTest {

    @Mock
    private ArrayToGridLocationConverter arrayToGridLocationConverter;

    private RoomGridFactory roomGridFactory;

    @BeforeEach
    void setUp() {
        this.roomGridFactory = new RoomGridFactory(arrayToGridLocationConverter);
    }

    @Test
    void create_whenRoomSizeIsNull_shouldThrowException() {
        assertThatThrownBy(() -> roomGridFactory.create(null, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("roomSize must have exactly two elements.");
    }

    @Test
    void create_whenRoomSizeIsEmpty_shouldThrowException() {
        assertThatThrownBy(() -> roomGridFactory.create(new int[]{}, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("roomSize must have exactly two elements.");
    }

    @Test
    void create_whenRoomSizeHasOneElement_shouldThrowException() {
        assertThatThrownBy(() -> roomGridFactory.create(new int[]{1}, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("roomSize must have exactly two elements.");
    }

    @Test
    void create_whenRoomSizeHasThreeElements_shouldThrowException() {
        assertThatThrownBy(() -> roomGridFactory.create(new int[]{1, 2, 3}, null))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("roomSize must have exactly two elements.");
    }

    @Test
    void create_shouldCallArrayToGridLocationConverter() {
        roomGridFactory.create(new int[]{1, 2}, new int[][]{{1, 2}, {3, 4}});
        verify(arrayToGridLocationConverter).convert(new int[]{1, 2});
        verify(arrayToGridLocationConverter).convert(new int[]{3, 4});
    }

    @Test
    void create_whenRoomSizeIs6By7AndHasTwoDirtPatchLocations_shouldReturnRoomGridWithWidth6AndHeight7AndTwoDirtPatchLocations() {
        final var dirtLocation1 = new GridLocation(1, 2);
        final var dirtLocation2 = new GridLocation(3, 4);
        when(arrayToGridLocationConverter.convert(any())).thenReturn(
                dirtLocation1, dirtLocation2);

        final var result = roomGridFactory.create(new int[]{6, 7}, new int[][]{{1, 2}, {3, 4}});

        assertThat(result.getWidth()).isEqualTo(6);
        assertThat(result.getHeight()).isEqualTo(7);
        assertThat(result.getLocationOfDirtPatches()).containsExactly(dirtLocation1, dirtLocation2);

        verify(arrayToGridLocationConverter).convert(new int[]{1, 2});
        verify(arrayToGridLocationConverter).convert(new int[]{3, 4});
    }
}