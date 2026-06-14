package com.ontrainer.controller;

import com.ontrainer.dto.Dtos;
import com.ontrainer.entity.User;
import com.ontrainer.service.CheckInService;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/checkins")
public class CheckInController {
    private final CheckInService checkInService;
    public CheckInController(CheckInService checkInService) { this.checkInService = checkInService; }

    @PostMapping
    public ResponseEntity<Dtos.CheckInResponse> checkIn(@AuthenticationPrincipal User user,
            @RequestBody(required = false) Dtos.CheckInRequest req) {
        if (req == null) req = new Dtos.CheckInRequest();
        return ResponseEntity.status(HttpStatus.CREATED).body(checkInService.registerCheckIn(user.getId(), req));
    }

    @GetMapping("/history")
    public ResponseEntity<Dtos.CheckInHistoryResponse> history(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(checkInService.getHistory(user.getId()));
    }
}
