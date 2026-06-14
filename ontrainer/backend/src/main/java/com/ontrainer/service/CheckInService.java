package com.ontrainer.service;

import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.*;
import com.ontrainer.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CheckInService {
    private final CheckInRepository checkInRepo;
    private final RoutineRepository routineRepo;

    public CheckInService(CheckInRepository checkInRepo, RoutineRepository routineRepo) {
        this.checkInRepo = checkInRepo;
        this.routineRepo = routineRepo;
    }

    @Transactional
    public Dtos.CheckInResponse registerCheckIn(UUID userId, Dtos.CheckInRequest req) {
        User user = new User(); user.setId(userId);
        CheckIn checkIn = CheckIn.builder().user(user).build();
        if (req.getRoutineId() != null) {
            routineRepo.findByIdAndUserId(req.getRoutineId(), userId).ifPresent(checkIn::setRoutine);
        }
        checkIn = checkInRepo.save(checkIn);
        return toDto(checkIn);
    }

    public Dtos.CheckInHistoryResponse getHistory(UUID userId) {
        List<CheckIn> all = checkInRepo.findByUserIdOrderByCreatedAtDesc(userId);
        List<Dtos.CheckInResponse> dtos = all.stream().map(this::toDto).collect(Collectors.toList());
        return Dtos.CheckInHistoryResponse.builder()
                .checkIns(dtos).currentStreak(computeStreak(all)).totalCheckIns(all.size()).build();
    }

    private int computeStreak(List<CheckIn> checkIns) {
        if (checkIns.isEmpty()) return 0;
        Set<String> days = new HashSet<>();
        for (CheckIn c : checkIns) days.add(c.getCreatedAt().toLocalDate().toString());
        int streak = 0;
        LocalDateTime now = LocalDateTime.now();
        for (int i = 0; i < 365; i++) {
            if (days.contains(now.minusDays(i).toLocalDate().toString())) streak++;
            else if (i > 0) break;
        }
        return streak;
    }

    private Dtos.CheckInResponse toDto(CheckIn c) {
        return Dtos.CheckInResponse.builder().id(c.getId()).createdAt(c.getCreatedAt())
                .routineName(c.getRoutine() != null ? c.getRoutine().getName() : null).build();
    }
}
