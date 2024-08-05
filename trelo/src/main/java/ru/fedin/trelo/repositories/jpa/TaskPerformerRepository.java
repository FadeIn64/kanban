package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import ru.fedin.trelo.eintites.DeskContributor;
import ru.fedin.trelo.eintites.TaskPerformer;

import java.util.Optional;

public interface TaskPerformerRepository extends DeleteModifyJpaRepository<TaskPerformer, Integer> {
    Optional<TaskPerformer> findByContributor(@NotNull DeskContributor contributor);
}
