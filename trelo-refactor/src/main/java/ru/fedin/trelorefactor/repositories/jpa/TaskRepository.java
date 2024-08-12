package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}