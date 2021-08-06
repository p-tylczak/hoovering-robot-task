package com.example.model;

public class RobotCleaningResult {
    private final GridLocation finalPosition;
    private final int dirtPatchesCleaned;

    public RobotCleaningResult(GridLocation finalPosition, int dirtPatchesCleaned) {
        this.finalPosition = finalPosition;
        this.dirtPatchesCleaned = dirtPatchesCleaned;
    }

    public GridLocation getFinalPosition() {
        return finalPosition;
    }

    public int getDirtPatchesCleaned() {
        return dirtPatchesCleaned;
    }
}
