package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.TaskHistory;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {
}
