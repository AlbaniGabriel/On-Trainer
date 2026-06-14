package com.ontrainer.repository;

import com.ontrainer.entity.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface RoutineRepository extends JpaRepository<Routine, UUID> {
    List<Routine> findByUserId(UUID userId);
    Optional<Routine> findByIdAndUserId(UUID id, UUID userId);
}
