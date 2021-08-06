package com.example.factory;

import com.example.converter.ArrayToGridLocationConverter;
import com.example.exception.InvalidInputException;
import com.example.model.GridLocation;
import com.example.model.RoomGrid;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoomGridFactory {

    private final ArrayToGridLocationConverter arrayToGridLocationConverter;

    public RoomGridFactory(ArrayToGridLocationConverter arrayToGridLocationConverter) {
        this.arrayToGridLocationConverter = arrayToGridLocationConverter;
    }

    public RoomGrid create(int[] roomSize, int[][] dirtPatches) {
        if (roomSize == null || roomSize.length != 2) {
            throw new InvalidInputException("roomSize must have exactly two elements.");
        }

        return new RoomGrid(
                roomSize[0],
                roomSize[1],
                toGridLocation(dirtPatches));
    }

    private List<GridLocation> toGridLocation(int[][] dirtPatches) {
        var dirtyGridLocations = new ArrayList<GridLocation>();

        for (var dirtPatchLocation : dirtPatches) {
            final var gridLocation = arrayToGridLocationConverter.convert(dirtPatchLocation);
            dirtyGridLocations.add(gridLocation);
        }

        return dirtyGridLocations;
    }
}
