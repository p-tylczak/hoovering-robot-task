package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructionAuditRepository extends CrudRepository<InstructionAuditEntity, Integer> {
}
