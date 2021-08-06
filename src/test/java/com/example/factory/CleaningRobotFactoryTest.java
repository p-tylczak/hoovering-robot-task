package com.example.factory;

import com.example.exception.InvalidInputException;
import com.example.model.Patch;
import com.example.model.RoomGrid;
import com.example.service.RoomGridInitializer;
import com.example.service.RoomGridValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CleaningRobotFactoryTest {

    @Mock
    private RoomGridValidator roomGridValidator;
    @Mock
    private RoomGridInitializer roomGridInitializer;

    private CleaningRobotFactory cleaningRobotFactory;

    @BeforeEach
    void setUp() {
        this.cleaningRobotFactory = new CleaningRobotFactory(roomGridValidator, roomGridInitializer);
    }

    @Test
    void create_whenIsValidReturnsFalse_shouldCallIsValidAndThrowException() {
        when(roomGridValidator.isValid(any())).thenReturn(false);
        final var roomGrid = new RoomGrid(0, 0, Collections.emptyList());

        assertThatThrownBy(() -> cleaningRobotFactory.create(roomGrid))
                .isInstanceOf(InvalidInputException.class)
                .hasMessage("roomGrid is invalid.");

        verify(roomGridValidator).isValid(roomGrid);
    }

    @Test
    void create_whenIsValidReturnsTrue_shouldCallRoomGridInitializer() {
        final var grid = new Patch[5][6];
        when(roomGridValidator.isValid(any())).thenReturn(true);
        when(roomGridInitializer.initialize(anyInt(), anyInt(), any())).thenReturn(grid);
        final var roomGrid = new RoomGrid(5, 6, Collections.emptyList());

        cleaningRobotFactory.create(roomGrid);

        verify(roomGridInitializer).initialize(5, 6, Collections.emptyList());
    }
}