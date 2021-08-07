package com.example.service;

import com.example.factory.CleaningRobotFactory;
import com.example.model.GridLocation;
import com.example.model.RoomGrid;
import com.example.repository.RobotCleaningResultEntity;
import com.example.repository.RobotCleaningResultRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CleaningRobotServiceTest {

    @Mock
    private RobotCleaningResultRepository robotCleaningResultRepository;

    private CleaningRobotService cleaningRobotService;

    @BeforeEach
    void setUp() {
        this.cleaningRobotService = new CleaningRobotService(
                new CleaningRobotNavigator(new NextLocationCalculator()),
                new CleaningRobotFactory(new RoomGridValidator(), new RoomGridInitializer()),
                robotCleaningResultRepository);
    }

    @Test
    void startCleaning_whenSampleInputProvided_shouldReturnCorrectOutput() {
        final var locationOfDirtPatches = Arrays.asList(
                new GridLocation(1, 0),
                new GridLocation(2, 2),
                new GridLocation(2, 3));
        final var roomGrid = new RoomGrid(5, 5, locationOfDirtPatches);
        final var initialCleanerRobotLocation = new GridLocation(1, 2);
        final var navigationInstructions = Arrays.asList(
                Direction.NORTH,
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.EAST,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST,
                Direction.NORTH,
                Direction.WEST,
                Direction.WEST);

        final var result = cleaningRobotService.startCleaning(roomGrid, initialCleanerRobotLocation, navigationInstructions);

        assertThat(result.getFinalPosition()).isEqualTo(new GridLocation(1, 3));
        assertThat(result.getDirtPatchesCleaned()).isOne();
    }

    @Test
    void startCleaning_whenGridIs1By1AndNoDirtPatchesAndInitialLocationIsAtZeroZeroAndNoInstructionsAvailable_shouldReturnLocationAtZeroZeroAndNoCleanedPatches() {
        final var locationOfDirtPatches = Collections.<GridLocation>emptyList();
        final var roomGrid = new RoomGrid(1, 1, locationOfDirtPatches);
        final var initialCleanerRobotLocation = new GridLocation(0, 0);
        final var navigationInstructions = Collections.<Direction>emptyList();

        final var result = cleaningRobotService.startCleaning(roomGrid, initialCleanerRobotLocation, navigationInstructions);

        assertThat(result.getFinalPosition()).isEqualTo(new GridLocation(0, 0));
        assertThat(result.getDirtPatchesCleaned()).isZero();
    }

    @Test
    void startCleaning_whenGridIs1By1AndHasDirtPatchAtLocationZeroZeroAndInitialLocationIsAtZeroZeroAndNoInstructionsAvailable_shouldReturnLocationAtZeroZeroAndOneCleanedPatch() {
        final var locationOfDirtPatches = Collections.singletonList(new GridLocation(0, 0));
        final var roomGrid = new RoomGrid(1, 1, locationOfDirtPatches);
        final var initialCleanerRobotLocation = new GridLocation(0, 0);
        final var navigationInstructions = Collections.<Direction>emptyList();

        final var result = cleaningRobotService.startCleaning(roomGrid, initialCleanerRobotLocation, navigationInstructions);

        assertThat(result.getFinalPosition()).isEqualTo(new GridLocation(0, 0));
        assertThat(result.getDirtPatchesCleaned()).isOne();
    }

    @Test
    void startCleaning_whenGridIs2By2AndHasDirtPatchAtAllLocationsAndInitialLocationIsAtZeroZeroAndInstructionsNES_shouldReturnLocationAtOneZeroAndFourCleanedPatch() {
        final var locationOfDirtPatches = Arrays.asList(
                new GridLocation(0, 0),
                new GridLocation(0, 1),
                new GridLocation(1, 1),
                new GridLocation(1, 0));
        final var roomGrid = new RoomGrid(2, 2, locationOfDirtPatches);
        final var initialCleanerRobotLocation = new GridLocation(0, 0);
        final var navigationInstructions = Arrays.asList(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH);

        final var result = cleaningRobotService.startCleaning(roomGrid, initialCleanerRobotLocation, navigationInstructions);

        assertThat(result.getFinalPosition()).isEqualTo(new GridLocation(1, 0));
        assertThat(result.getDirtPatchesCleaned()).isEqualTo(4);
    }

    @Test
    void startCleaning_shouldCallRepositoryWithCorrectArguments() {
        final var locationOfDirtPatches = Arrays.asList(
                new GridLocation(0, 0),
                new GridLocation(1, 1));
        final var roomGrid = new RoomGrid(2, 2, locationOfDirtPatches);
        final var initialCleanerRobotLocation = new GridLocation(0, 0);
        final var navigationInstructions = Arrays.asList(
                Direction.NORTH,
                Direction.EAST,
                Direction.SOUTH,
                Direction.WEST);

        final var result = cleaningRobotService.startCleaning(roomGrid, initialCleanerRobotLocation, navigationInstructions);

        final int x = 0;
        final int y = 0;
        final int count = 2;

        assertThat(result.getFinalPosition()).isEqualTo(new GridLocation(x, y));
        assertThat(result.getDirtPatchesCleaned()).isEqualTo(count);

        final var expected = new RobotCleaningResultEntity(x, y, count);
        verify(robotCleaningResultRepository).save(expected);
    }
}
