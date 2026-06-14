package com.ontrainer.repository;

import com.ontrainer.entity.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.*;

public interface ExerciseRepository extends JpaRepository<Exercise, UUID> {
    @Query("SELECT e FROM Exercise e WHERE e.isActive = true")
    List<Exercise> findAllActive();
}
