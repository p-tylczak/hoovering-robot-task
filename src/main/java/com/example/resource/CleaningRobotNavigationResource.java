package com.example.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Valid
public class CleaningRobotNavigationResource {

    @NotNull
    @Size(min = 2, max = 2)
    private final int[] roomSize;
    @NotNull
    @Size(min = 2, max = 2)
    private final int[] startingPosition;
    private final int[][] dirtPatches;
    private final String navigationInstructions;

    public CleaningRobotNavigationResource(@JsonProperty(value = "roomSize", required = true) int[] roomSize,
                                           @JsonProperty(value = "coords", required = true) int[] startingPosition,
                                           @JsonProperty(value = "patches", required = true) int[][] dirtPatches,
                                           @JsonProperty(value = "instructions", required = true) String navigationInstructions) {
        this.roomSize = roomSize;
        this.startingPosition = startingPosition;
        this.dirtPatches = dirtPatches;
        this.navigationInstructions = navigationInstructions;
    }

    public int[] getRoomSize() {
        return roomSize;
    }

    public int[] getStartingPosition() {
        return startingPosition;
    }

    public int[][] getDirtPatches() {
        return dirtPatches;
    }

    public String getNavigationInstructions() {
        return navigationInstructions;
    }
}
