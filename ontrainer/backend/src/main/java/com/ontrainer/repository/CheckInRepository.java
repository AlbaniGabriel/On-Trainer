package com.ontrainer.repository;

import com.ontrainer.entity.CheckIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDateTime;
import java.util.*;

public interface CheckInRepository extends JpaRepository<CheckIn, UUID> {
    List<CheckIn> findByUserIdOrderByCreatedAtDesc(UUID userId);

    @Query("SELECT COUNT(c) FROM CheckIn c WHERE c.user.id = :userId AND c.createdAt >= :since")
    long countByUserIdSince(@Param("userId") UUID userId, @Param("since") LocalDateTime since);
}
