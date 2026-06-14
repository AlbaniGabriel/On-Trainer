package com.ontrainer.entity;

import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "exercises")
public class Exercise {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @OneToMany(mappedBy = "exercise", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ExerciseMuscleGroup> exerciseMuscleGroups = new ArrayList<>();

    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
    public List<ExerciseMuscleGroup> getExerciseMuscleGroups() { return exerciseMuscleGroups; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final Exercise e = new Exercise();
        public Builder name(String v) { e.name = v; return this; }
        public Builder description(String v) { e.description = v; return this; }
        public Builder isActive(Boolean v) { e.isActive = v; return this; }
        public Exercise build() { return e; }
    }
}
