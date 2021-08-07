package com.example.repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "INSTRUCTION_AUDIT")
public class InstructionAuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "ROOM_WIDTH")
    private int roomWidth;
    @Column(name = "ROOM_HEIGHT")
    private int roomHeight;
    @Column(name = "START_COORD_X")
    private int getStartCoordinateX;
    @Column(name = "START_COORD_Y")
    private int startCoordinateY;
    @Column(name = "DIRT_PATCHES")
    private String dirtPatches;
    @Column(name = "INSTRUCTIONS")
    private String instructions;

    public InstructionAuditEntity(int roomWidth, int roomHeight, int getStartCoordinateX, int startCoordinateY, String dirtPatches, String instructions) {
        this.roomWidth = roomWidth;
        this.roomHeight = roomHeight;
        this.getStartCoordinateX = getStartCoordinateX;
        this.startCoordinateY = startCoordinateY;
        this.dirtPatches = dirtPatches;
        this.instructions = instructions;
    }

    public long getId() {
        return id;
    }

    public int getRoomWidth() {
        return roomWidth;
    }

    public int getRoomHeight() {
        return roomHeight;
    }

    public int getGetStartCoordinateX() {
        return getStartCoordinateX;
    }

    public int getStartCoordinateY() {
        return startCoordinateY;
    }

    public String getDirtPatches() {
        return dirtPatches;
    }

    public String getInstructions() {
        return instructions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InstructionAuditEntity that = (InstructionAuditEntity) o;
        return id == that.id && roomWidth == that.roomWidth && roomHeight == that.roomHeight && getStartCoordinateX == that.getStartCoordinateX && startCoordinateY == that.startCoordinateY && Objects.equals(dirtPatches, that.dirtPatches) && Objects.equals(instructions, that.instructions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, roomWidth, roomHeight, getStartCoordinateX, startCoordinateY, dirtPatches, instructions);
    }
}
