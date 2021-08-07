package com.example.service;

import com.example.model.GridLocation;
import com.example.repository.InstructionAuditEntity;
import com.example.repository.InstructionAuditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class InstructionAuditServiceTest {

    @Mock
    private InstructionAuditRepository instructionAuditRepository;

    private InstructionAuditService instructionAuditService;

    @BeforeEach
    void setUp() {
        this.instructionAuditService = new InstructionAuditService(instructionAuditRepository);
    }

    @Test
    void save_shouldCallRepositoryWithCorrectArguments() {
        final var dirtPatches = Arrays.asList(
                new GridLocation(5, 6),
                new GridLocation(7, 8));

        instructionAuditService.save(1, 2, 3, 4, dirtPatches, "NNEESSWW");

        final var expected = new InstructionAuditEntity(1, 2, 3, 4, "5:6,7:8", "NNEESSWW");
        verify(instructionAuditRepository).save(expected);
    }
}