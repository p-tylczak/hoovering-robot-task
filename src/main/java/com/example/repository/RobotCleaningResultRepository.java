package com.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobotCleaningResultRepository extends CrudRepository<RobotCleaningResultEntity, Integer> {
}
