package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskTask;
import ru.fedin.trelo.eintites.TaskHistory;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskHistoryRepository extends DeleteModifyJpaRepository<TaskHistory, Integer> {

    List<TaskHistory> findAllByTask(@NotNull DeskTask task);

    List<TaskHistory> findAllByTaskAndChangeDateBetween(@NotNull DeskTask task,
                                                        LocalDateTime changeDate,
                                                        LocalDateTime changeDate2);

}
