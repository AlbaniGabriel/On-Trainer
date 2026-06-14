package com.ontrainer.seed;

import com.ontrainer.entity.*;
import com.ontrainer.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
    private final UserRepository userRepository;
    private final MuscleGroupRepository muscleGroupRepository;
    private final ExerciseRepository exerciseRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(UserRepository userRepository, MuscleGroupRepository muscleGroupRepository,
                      ExerciseRepository exerciseRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.muscleGroupRepository = muscleGroupRepository;
        this.exerciseRepository = exerciseRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override @Transactional
    public void run(String... args) {
        log.info("Iniciando seed de dados...");
        seedMuscleGroupsAndExercises();
        seedAdminUser();
        log.info("Seed concluido! Usuario: admin@ontrainer.com / admin123");
    }

    private void seedAdminUser() {
        if (userRepository.existsByEmail("admin@ontrainer.com")) return;
        User admin = User.builder().username("Admin OnTrainer")
                .email("admin@ontrainer.com")
                .passwordHash(passwordEncoder.encode("admin123")).build();
        userRepository.save(admin);
        log.info("Usuario admin criado: admin@ontrainer.com / admin123");
    }

    private void seedMuscleGroupsAndExercises() {
        MuscleGroup peito   = mg("Peito");
        MuscleGroup pernas  = mg("Pernas");
        MuscleGroup costas  = mg("Costas");
        MuscleGroup ombros  = mg("Ombros");
        MuscleGroup biceps  = mg("Biceps");
        MuscleGroup triceps = mg("Triceps");
        MuscleGroup core    = mg("Core / Abdomen");
        MuscleGroup gluteos = mg("Gluteos");

        ex("Supino Reto com Barra",      "Exercicio composto para peitoral maior.",              List.of(peito, triceps, ombros));
        ex("Agachamento Livre",          "Movimento composto para pernas e gluteos.",             List.of(pernas, gluteos, core));
        ex("Levantamento Terra",         "Exercicio de cadeia posterior completa.",               List.of(costas, pernas, gluteos, core));
        ex("Barra Fixa (Pull-up)",       "Treino de costas e biceps com peso corporal.",          List.of(costas, biceps));
        ex("Desenvolvimento com Halteres","Exercicio para deltoides e ombros.",                  List.of(ombros, triceps));
        ex("Rosca Direta com Barra",     "Isolamento do biceps braquial.",                        List.of(biceps));
        ex("Triceps Testa com Barra",    "Isolamento para a cabeca longa do triceps.",            List.of(triceps));
        ex("Leg Press 45",               "Alternativa ao agachamento para quadriceps.",           List.of(pernas, gluteos));
        ex("Prancha Abdominal (Plank)",  "Exercicio isometrico para estabilidade do core.",       List.of(core));
        ex("Remada Curvada com Barra",   "Exercicio composto para espessura das costas.",         List.of(costas, biceps));
        log.info("{} exercicios disponiveis.", exerciseRepository.count());
    }

    private MuscleGroup mg(String name) {
        return muscleGroupRepository.findByName(name).orElseGet(() ->
                muscleGroupRepository.save(MuscleGroup.builder().name(name).build()));
    }

    private void ex(String name, String desc, List<MuscleGroup> groups) {
        boolean exists = exerciseRepository.findAllActive().stream().anyMatch(e -> e.getName().equals(name));
        if (exists) return;
        Exercise e = exerciseRepository.save(Exercise.builder().name(name).description(desc).isActive(true).build());
        for (MuscleGroup mg : groups) {
            e.getExerciseMuscleGroups().add(
                ExerciseMuscleGroup.builder().exercise(e).muscleGroup(mg).build());
        }
        exerciseRepository.save(e);
    }
}
