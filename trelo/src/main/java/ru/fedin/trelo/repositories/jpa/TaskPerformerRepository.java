package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.TaskPerformer;

public interface TaskPerformerRepository extends JpaRepository<TaskPerformer, Integer> {
}
