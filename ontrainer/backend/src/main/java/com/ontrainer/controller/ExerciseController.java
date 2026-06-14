package com.ontrainer.controller;

import com.ontrainer.dto.Dtos;
import com.ontrainer.service.ExerciseService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    public ExerciseController(ExerciseService exerciseService) { this.exerciseService = exerciseService; }

    @GetMapping
    public ResponseEntity<List<Dtos.ExerciseResponse>> listActive() { return ResponseEntity.ok(exerciseService.listActive()); }

    @GetMapping("/{id}")
    public ResponseEntity<Dtos.ExerciseResponse> getById(@PathVariable UUID id) { return ResponseEntity.ok(exerciseService.getById(id)); }

    @PostMapping
    public ResponseEntity<Dtos.ExerciseResponse> create(@Valid @RequestBody Dtos.CreateExerciseRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(exerciseService.create(req));
    }

    @GetMapping("/muscle-groups")
    public ResponseEntity<List<Dtos.MuscleGroupResponse>> listMuscleGroups() { return ResponseEntity.ok(exerciseService.listMuscleGroups()); }
}
