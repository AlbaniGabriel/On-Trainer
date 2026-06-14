package com.ontrainer.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "muscle_groups")
public class MuscleGroup {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(unique = true, nullable = false)
    private String name;

    public UUID getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private final MuscleGroup m = new MuscleGroup();
        public Builder name(String v) { m.name = v; return this; }
        public MuscleGroup build() { return m; }
    }
}
