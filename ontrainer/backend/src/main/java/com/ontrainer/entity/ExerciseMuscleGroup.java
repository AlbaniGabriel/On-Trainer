package com.ontrainer.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "exercise_muscle_groups")
public class ExerciseMuscleGroup {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "muscle_group_id", nullable = false)
    private MuscleGroup muscleGroup;

    public UUID getId() { return id; }
    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
    public MuscleGroup getMuscleGroup() { return muscleGroup; }
    public void setMuscleGroup(MuscleGroup muscleGroup) { this.muscleGroup = muscleGroup; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final ExerciseMuscleGroup e = new ExerciseMuscleGroup();
        public Builder exercise(Exercise v) { e.exercise = v; return this; }
        public Builder muscleGroup(MuscleGroup v) { e.muscleGroup = v; return this; }
        public ExerciseMuscleGroup build() { return e; }
    }
}
