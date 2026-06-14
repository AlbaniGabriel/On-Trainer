package com.ontrainer.service;

import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.*;
import com.ontrainer.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepo;
    private final MuscleGroupRepository muscleGroupRepo;

    public ExerciseService(ExerciseRepository exerciseRepo, MuscleGroupRepository muscleGroupRepo) {
        this.exerciseRepo = exerciseRepo;
        this.muscleGroupRepo = muscleGroupRepo;
    }

    public List<Dtos.ExerciseResponse> listActive() {
        return exerciseRepo.findAllActive().stream().map(this::toDto).collect(Collectors.toList());
    }

    public Dtos.ExerciseResponse getById(UUID id) {
        return toDto(exerciseRepo.findById(id).orElseThrow(() -> new RuntimeException("Exercicio nao encontrado")));
    }

    @Transactional
    public Dtos.ExerciseResponse create(Dtos.CreateExerciseRequest req) {
        Exercise exercise = Exercise.builder().name(req.getName()).description(req.getDescription()).isActive(true).build();
        exercise = exerciseRepo.save(exercise);
        if (req.getMuscleGroupIds() != null) {
            for (UUID mgId : req.getMuscleGroupIds()) {
                MuscleGroup mg = muscleGroupRepo.findById(mgId)
                        .orElseThrow(() -> new RuntimeException("Grupo muscular nao encontrado: " + mgId));
                ExerciseMuscleGroup emg = ExerciseMuscleGroup.builder().exercise(exercise).muscleGroup(mg).build();
                exercise.getExerciseMuscleGroups().add(emg);
            }
            exercise = exerciseRepo.save(exercise);
        }
        return toDto(exercise);
    }

    public List<Dtos.MuscleGroupResponse> listMuscleGroups() {
        return muscleGroupRepo.findAll().stream()
                .map(mg -> Dtos.MuscleGroupResponse.builder().id(mg.getId()).name(mg.getName()).build())
                .collect(Collectors.toList());
    }

    public Dtos.ExerciseResponse toDto(Exercise e) {
        List<Dtos.MuscleGroupResponse> mgs = e.getExerciseMuscleGroups().stream()
                .map(emg -> Dtos.MuscleGroupResponse.builder()
                        .id(emg.getMuscleGroup().getId()).name(emg.getMuscleGroup().getName()).build())
                .collect(Collectors.toList());
        return Dtos.ExerciseResponse.builder()
                .id(e.getId()).name(e.getName()).description(e.getDescription())
                .isActive(e.getIsActive()).muscleGroups(mgs).build();
    }
}
