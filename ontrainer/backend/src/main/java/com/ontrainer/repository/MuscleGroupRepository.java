package com.ontrainer.repository;

import com.ontrainer.entity.MuscleGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface MuscleGroupRepository extends JpaRepository<MuscleGroup, UUID> {
    Optional<MuscleGroup> findByName(String name);
}
