package com.example.repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "ROBOT_CLEANING_RESULT")
public class RobotCleaningResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "END_COORD_X")
    private int endCoordinateX;
    @Column(name = "END_COORD_Y")
    private int endCoordinateY;
    @Column(name = "CLEANED_PATCHES_COUNT")
    private int cleanedPatchesCount;

    public RobotCleaningResultEntity(int endCoordinateX, int endCoordinateY, int cleanedPatchesCount) {
        this.endCoordinateX = endCoordinateX;
        this.endCoordinateY = endCoordinateY;
        this.cleanedPatchesCount = cleanedPatchesCount;
    }

    public long getId() {
        return id;
    }

    public int getEndCoordinateX() {
        return endCoordinateX;
    }

    public int getEndCoordinateY() {
        return endCoordinateY;
    }

    public int getCleanedPatchesCount() {
        return cleanedPatchesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RobotCleaningResultEntity that = (RobotCleaningResultEntity) o;
        return id == that.id && endCoordinateX == that.endCoordinateX && endCoordinateY == that.endCoordinateY && cleanedPatchesCount == that.cleanedPatchesCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, endCoordinateX, endCoordinateY, cleanedPatchesCount);
    }
}
