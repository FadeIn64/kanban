package ru.fedin.trelorefactor.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.History;
import ru.fedin.trelorefactor.eintites.Task;

import java.time.LocalDateTime;
import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Long> {

    List<History> findAllByTaskAndChangeDateBetween(@NotNull Task task,
                                                        LocalDateTime changeDate,
                                                        LocalDateTime changeDate2);

}