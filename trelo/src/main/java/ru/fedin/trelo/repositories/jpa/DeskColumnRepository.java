package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskColumn;

import java.util.List;
import java.util.Optional;

public interface DeskColumnRepository extends DeleteModifyJpaRepository<DeskColumn, Integer> {

    Optional<DeskColumn> findByPrevAndDesk(Integer prev, @NotNull Integer desk);
    Optional<DeskColumn> findByNextAndDesk(Integer next, @NotNull Integer desk);

    List<DeskColumn> findAllByDesk(@NotNull Integer desk);
    List<DeskColumn> findAllByDeskOrderById(@NotNull Integer desk);
}
