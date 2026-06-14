package com.ontrainer.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "routine_exercises")
public class RoutineExercise {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "routine_id", nullable = false)
    private Routine routine;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;

    @Column(name = "order_position")
    private Integer orderPosition;

    @Column(name = "target_sets")
    private Integer targetSets;

    @Column(name = "target_reps")
    private Integer targetReps;

    @Column(name = "target_weight", precision = 7, scale = 2)
    private BigDecimal targetWeight;

    @Column(columnDefinition = "TEXT")
    private String notes;

    public UUID getId() { return id; }
    public Routine getRoutine() { return routine; }
    public void setRoutine(Routine routine) { this.routine = routine; }
    public Exercise getExercise() { return exercise; }
    public void setExercise(Exercise exercise) { this.exercise = exercise; }
    public Integer getOrderPosition() { return orderPosition; }
    public void setOrderPosition(Integer orderPosition) { this.orderPosition = orderPosition; }
    public Integer getTargetSets() { return targetSets; }
    public void setTargetSets(Integer targetSets) { this.targetSets = targetSets; }
    public Integer getTargetReps() { return targetReps; }
    public void setTargetReps(Integer targetReps) { this.targetReps = targetReps; }
    public BigDecimal getTargetWeight() { return targetWeight; }
    public void setTargetWeight(BigDecimal targetWeight) { this.targetWeight = targetWeight; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final RoutineExercise re = new RoutineExercise();
        public Builder routine(Routine v) { re.routine = v; return this; }
        public Builder exercise(Exercise v) { re.exercise = v; return this; }
        public Builder orderPosition(Integer v) { re.orderPosition = v; return this; }
        public Builder targetSets(Integer v) { re.targetSets = v; return this; }
        public Builder targetReps(Integer v) { re.targetReps = v; return this; }
        public Builder targetWeight(BigDecimal v) { re.targetWeight = v; return this; }
        public Builder notes(String v) { re.notes = v; return this; }
        public RoutineExercise build() { return re; }
    }
}
