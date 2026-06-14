package com.ontrainer.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

public class Dtos {

    public static class RegisterRequest {
        @NotBlank private String username;
        @Email @NotBlank private String email;
        @NotBlank @Size(min = 6) private String password;
        private String phone;
        public String getUsername() { return username; }
        public void setUsername(String v) { this.username = v; }
        public String getEmail() { return email; }
        public void setEmail(String v) { this.email = v; }
        public String getPassword() { return password; }
        public void setPassword(String v) { this.password = v; }
        public String getPhone() { return phone; }
        public void setPhone(String v) { this.phone = v; }
    }

    public static class LoginRequest {
        @Email @NotBlank private String email;
        @NotBlank private String password;
        public String getEmail() { return email; }
        public void setEmail(String v) { this.email = v; }
        public String getPassword() { return password; }
        public void setPassword(String v) { this.password = v; }
    }

    public static class AuthResponse {
        private String token; private UUID userId; private String username; private String email;
        public String getToken() { return token; }
        public UUID getUserId() { return userId; }
        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final AuthResponse r = new AuthResponse();
            public Builder token(String v) { r.token = v; return this; }
            public Builder userId(UUID v) { r.userId = v; return this; }
            public Builder username(String v) { r.username = v; return this; }
            public Builder email(String v) { r.email = v; return this; }
            public AuthResponse build() { return r; }
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class UserResponse {
        private UUID id; private String username; private String email; private String phone; private LocalDateTime createdAt;
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final UserResponse r = new UserResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder username(String v) { r.username = v; return this; }
            public Builder email(String v) { r.email = v; return this; }
            public Builder phone(String v) { r.phone = v; return this; }
            public Builder createdAt(LocalDateTime v) { r.createdAt = v; return this; }
            public UserResponse build() { return r; }
        }
    }

    public static class MuscleGroupResponse {
        private UUID id; private String name;
        public UUID getId() { return id; }
        public String getName() { return name; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final MuscleGroupResponse r = new MuscleGroupResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder name(String v) { r.name = v; return this; }
            public MuscleGroupResponse build() { return r; }
        }
    }

    public static class ExerciseResponse {
        private UUID id; private String name; private String description; private Boolean isActive; private List<MuscleGroupResponse> muscleGroups;
        public UUID getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Boolean getIsActive() { return isActive; }
        public List<MuscleGroupResponse> getMuscleGroups() { return muscleGroups; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final ExerciseResponse r = new ExerciseResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder name(String v) { r.name = v; return this; }
            public Builder description(String v) { r.description = v; return this; }
            public Builder isActive(Boolean v) { r.isActive = v; return this; }
            public Builder muscleGroups(List<MuscleGroupResponse> v) { r.muscleGroups = v; return this; }
            public ExerciseResponse build() { return r; }
        }
    }

    public static class CreateExerciseRequest {
        @NotBlank private String name; private String description; private List<UUID> muscleGroupIds;
        public String getName() { return name; }
        public void setName(String v) { this.name = v; }
        public String getDescription() { return description; }
        public void setDescription(String v) { this.description = v; }
        public List<UUID> getMuscleGroupIds() { return muscleGroupIds; }
        public void setMuscleGroupIds(List<UUID> v) { this.muscleGroupIds = v; }
    }

    public static class CreateRoutineRequest {
        @NotBlank private String name; private String description;
        @Min(0) @Max(6) private Integer dayOfWeek;
        private List<RoutineExerciseRequest> exercises;
        public String getName() { return name; }
        public void setName(String v) { this.name = v; }
        public String getDescription() { return description; }
        public void setDescription(String v) { this.description = v; }
        public Integer getDayOfWeek() { return dayOfWeek; }
        public void setDayOfWeek(Integer v) { this.dayOfWeek = v; }
        public List<RoutineExerciseRequest> getExercises() { return exercises; }
        public void setExercises(List<RoutineExerciseRequest> v) { this.exercises = v; }
    }

    public static class RoutineExerciseRequest {
        @NotNull private UUID exerciseId; private Integer orderPosition;
        @Min(1) private Integer targetSets; @Min(1) private Integer targetReps;
        @DecimalMin("0.0") private BigDecimal targetWeight; private String notes;
        public UUID getExerciseId() { return exerciseId; }
        public void setExerciseId(UUID v) { this.exerciseId = v; }
        public Integer getOrderPosition() { return orderPosition; }
        public void setOrderPosition(Integer v) { this.orderPosition = v; }
        public Integer getTargetSets() { return targetSets; }
        public void setTargetSets(Integer v) { this.targetSets = v; }
        public Integer getTargetReps() { return targetReps; }
        public void setTargetReps(Integer v) { this.targetReps = v; }
        public BigDecimal getTargetWeight() { return targetWeight; }
        public void setTargetWeight(BigDecimal v) { this.targetWeight = v; }
        public String getNotes() { return notes; }
        public void setNotes(String v) { this.notes = v; }
    }

    public static class RoutineResponse {
        private UUID id; private String name; private String description;
        private Integer dayOfWeek; private String dayName;
        private List<RoutineExerciseResponse> exercises; private LocalDateTime createdAt;
        public UUID getId() { return id; }
        public String getName() { return name; }
        public String getDescription() { return description; }
        public Integer getDayOfWeek() { return dayOfWeek; }
        public String getDayName() { return dayName; }
        public List<RoutineExerciseResponse> getExercises() { return exercises; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final RoutineResponse r = new RoutineResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder name(String v) { r.name = v; return this; }
            public Builder description(String v) { r.description = v; return this; }
            public Builder dayOfWeek(Integer v) { r.dayOfWeek = v; return this; }
            public Builder dayName(String v) { r.dayName = v; return this; }
            public Builder exercises(List<RoutineExerciseResponse> v) { r.exercises = v; return this; }
            public Builder createdAt(LocalDateTime v) { r.createdAt = v; return this; }
            public RoutineResponse build() { return r; }
        }
    }

    public static class RoutineExerciseResponse {
        private UUID id; private ExerciseResponse exercise; private Integer orderPosition;
        private Integer targetSets; private Integer targetReps; private BigDecimal targetWeight; private String notes;
        public UUID getId() { return id; }
        public ExerciseResponse getExercise() { return exercise; }
        public Integer getOrderPosition() { return orderPosition; }
        public Integer getTargetSets() { return targetSets; }
        public Integer getTargetReps() { return targetReps; }
        public BigDecimal getTargetWeight() { return targetWeight; }
        public String getNotes() { return notes; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final RoutineExerciseResponse r = new RoutineExerciseResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder exercise(ExerciseResponse v) { r.exercise = v; return this; }
            public Builder orderPosition(Integer v) { r.orderPosition = v; return this; }
            public Builder targetSets(Integer v) { r.targetSets = v; return this; }
            public Builder targetReps(Integer v) { r.targetReps = v; return this; }
            public Builder targetWeight(BigDecimal v) { r.targetWeight = v; return this; }
            public Builder notes(String v) { r.notes = v; return this; }
            public RoutineExerciseResponse build() { return r; }
        }
    }

    public static class CheckInRequest {
        private UUID routineId;
        public UUID getRoutineId() { return routineId; }
        public void setRoutineId(UUID v) { this.routineId = v; }
    }

    public static class CheckInResponse {
        private UUID id; private LocalDateTime createdAt; private String routineName;
        public UUID getId() { return id; }
        public LocalDateTime getCreatedAt() { return createdAt; }
        public String getRoutineName() { return routineName; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final CheckInResponse r = new CheckInResponse();
            public Builder id(UUID v) { r.id = v; return this; }
            public Builder createdAt(LocalDateTime v) { r.createdAt = v; return this; }
            public Builder routineName(String v) { r.routineName = v; return this; }
            public CheckInResponse build() { return r; }
        }
    }

    public static class CheckInHistoryResponse {
        private List<CheckInResponse> checkIns; private int currentStreak; private int totalCheckIns;
        public List<CheckInResponse> getCheckIns() { return checkIns; }
        public int getCurrentStreak() { return currentStreak; }
        public int getTotalCheckIns() { return totalCheckIns; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final CheckInHistoryResponse r = new CheckInHistoryResponse();
            public Builder checkIns(List<CheckInResponse> v) { r.checkIns = v; return this; }
            public Builder currentStreak(int v) { r.currentStreak = v; return this; }
            public Builder totalCheckIns(int v) { r.totalCheckIns = v; return this; }
            public CheckInHistoryResponse build() { return r; }
        }
    }

    public static class ErrorResponse {
        private int status; private String message; private LocalDateTime timestamp;
        public int getStatus() { return status; }
        public String getMessage() { return message; }
        public LocalDateTime getTimestamp() { return timestamp; }
        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private final ErrorResponse r = new ErrorResponse();
            public Builder status(int v) { r.status = v; return this; }
            public Builder message(String v) { r.message = v; return this; }
            public Builder timestamp(LocalDateTime v) { r.timestamp = v; return this; }
            public ErrorResponse build() { return r; }
        }
    }
}
