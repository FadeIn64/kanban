package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskTask;
import ru.fedin.trelo.eintites.TaskHistory;

import java.util.List;

public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Integer> {

    List<TaskHistory> findAllByTask(@NotNull DeskTask task);

}
