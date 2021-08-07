package com.example.service;

import com.example.model.GridLocation;
import com.example.repository.InstructionAuditEntity;
import com.example.repository.InstructionAuditRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructionAuditService {

    private final InstructionAuditRepository instructionAuditRepository;

    public InstructionAuditService(InstructionAuditRepository instructionAuditRepository) {
        this.instructionAuditRepository = instructionAuditRepository;
    }

    public InstructionAuditEntity save(int width, int height, int xCoordinate, int yCoordinate, List<GridLocation> locationOfDirtPatches, String navigationInstructions) {
        return instructionAuditRepository.save(
                new InstructionAuditEntity(
                        width,
                        height,
                        xCoordinate,
                        yCoordinate,
                        toCsv(locationOfDirtPatches),
                        navigationInstructions));
    }

    private String toCsv(List<GridLocation> locationOfDirtPatches) {
        return locationOfDirtPatches.stream()
                .map(loc -> loc.getXCoordinate() + ":" + loc.getYCoordinate())
                .collect(Collectors.joining(","));
    }
}
