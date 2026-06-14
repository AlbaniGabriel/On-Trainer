package com.ontrainer.controller;

import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.User;
import com.ontrainer.service.RoutineService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/routines")
public class RoutineController {
    private final RoutineService routineService;
    public RoutineController(RoutineService routineService) { this.routineService = routineService; }

    @PostMapping
    public ResponseEntity<Dtos.RoutineResponse> create(@AuthenticationPrincipal User user, @Valid @RequestBody Dtos.CreateRoutineRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(routineService.create(user.getId(), req));
    }

    @GetMapping
    public ResponseEntity<List<Dtos.RoutineResponse>> listMine(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(routineService.listByUser(user.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Dtos.RoutineResponse> getById(@AuthenticationPrincipal User user, @PathVariable UUID id) {
        return ResponseEntity.ok(routineService.getById(id, user.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@AuthenticationPrincipal User user, @PathVariable UUID id) {
        routineService.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
