package com.ontrainer.service;

import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.*;
import com.ontrainer.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RoutineService {
    private static final String[] DAY_NAMES = {"Domingo","Segunda","Terca","Quarta","Quinta","Sexta","Sabado"};
    private final RoutineRepository routineRepo;
    private final ExerciseRepository exerciseRepo;
    private final CheckInRepository checkInRepo;
    private final ExerciseService exerciseService;

    public RoutineService(RoutineRepository routineRepo, ExerciseRepository exerciseRepo,
                          CheckInRepository checkInRepo, ExerciseService exerciseService) {
        this.routineRepo = routineRepo;
        this.exerciseRepo = exerciseRepo;
        this.checkInRepo = checkInRepo;
        this.exerciseService = exerciseService;
    }

    @Transactional
    public Dtos.RoutineResponse create(UUID userId, Dtos.CreateRoutineRequest req) {
        User user = new User(); user.setId(userId);
        Routine routine = Routine.builder().user(user).name(req.getName())
                .description(req.getDescription()).dayOfWeek(req.getDayOfWeek()).build();
        if (req.getExercises() != null) {
            for (Dtos.RoutineExerciseRequest er : req.getExercises()) {
                Exercise ex = exerciseRepo.findById(er.getExerciseId())
                        .orElseThrow(() -> new RuntimeException("Exercicio nao encontrado"));
                RoutineExercise re = RoutineExercise.builder()
                        .routine(routine).exercise(ex).orderPosition(er.getOrderPosition())
                        .targetSets(er.getTargetSets()).targetReps(er.getTargetReps())
                        .targetWeight(er.getTargetWeight()).notes(er.getNotes()).build();
                routine.getExercises().add(re);
            }
        }
        return toDto(routineRepo.save(routine));
    }

    public List<Dtos.RoutineResponse> listByUser(UUID userId) {
        return routineRepo.findByUserId(userId).stream().map(this::toDto).collect(Collectors.toList());
    }

    public Dtos.RoutineResponse getById(UUID id, UUID userId) {
        return toDto(routineRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Rotina nao encontrada")));
    }

    @Transactional
    public void delete(UUID id, UUID userId) {
        Routine routine = routineRepo.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new RuntimeException("Rotina nao encontrada"));
        // Desvincular check-ins antes de deletar
        List<CheckIn> checkins = checkInRepo.findByUserIdOrderByCreatedAtDesc(userId);
        for (CheckIn c : checkins) {
            if (c.getRoutine() != null && c.getRoutine().getId().equals(id)) {
                c.setRoutine(null);
                checkInRepo.save(c);
            }
        }
        routineRepo.delete(routine);
    }

    private Dtos.RoutineResponse toDto(Routine r) {
        List<Dtos.RoutineExerciseResponse> exercises = r.getExercises().stream()
                .map(re -> Dtos.RoutineExerciseResponse.builder()
                        .id(re.getId()).exercise(exerciseService.toDto(re.getExercise()))
                        .orderPosition(re.getOrderPosition()).targetSets(re.getTargetSets())
                        .targetReps(re.getTargetReps()).targetWeight(re.getTargetWeight())
                        .notes(re.getNotes()).build())
                .collect(Collectors.toList());
        String dayName = (r.getDayOfWeek() != null && r.getDayOfWeek() >= 0 && r.getDayOfWeek() <= 6)
                ? DAY_NAMES[r.getDayOfWeek()] : null;
        return Dtos.RoutineResponse.builder()
                .id(r.getId()).name(r.getName()).description(r.getDescription())
                .dayOfWeek(r.getDayOfWeek()).dayName(dayName)
                .exercises(exercises).createdAt(r.getCreatedAt()).build();
    }
}
