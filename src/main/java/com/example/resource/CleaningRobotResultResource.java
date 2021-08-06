package com.example.resource;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CleaningRobotResultResource {

    private final int[] finalPosition;
    private final int dirtPatchesCleaned;

    public CleaningRobotResultResource(int[] finalPosition, int dirtPatchesCleaned) {
        this.finalPosition = finalPosition;
        this.dirtPatchesCleaned = dirtPatchesCleaned;
    }

    @JsonProperty("coords")
    public int[] getFinalPosition() {
        return finalPosition;
    }

    @JsonProperty("patches")
    public int getDirtPatchesCleaned() {
        return dirtPatchesCleaned;
    }
}
