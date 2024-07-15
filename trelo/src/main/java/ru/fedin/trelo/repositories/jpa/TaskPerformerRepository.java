package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskContributor;
import ru.fedin.trelo.eintites.TaskPerformer;

import java.util.Optional;

public interface TaskPerformerRepository extends JpaRepository<TaskPerformer, Integer> {
    Optional<TaskPerformer> findByContributor(@NotNull DeskContributor contributor);
}
