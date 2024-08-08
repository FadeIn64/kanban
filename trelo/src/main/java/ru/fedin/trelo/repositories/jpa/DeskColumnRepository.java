package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import ru.fedin.trelo.eintites.Desk;
import ru.fedin.trelo.eintites.DeskColumn;

import java.util.List;
import java.util.Optional;

public interface DeskColumnRepository extends DeleteModifyJpaRepository<DeskColumn, Integer> {

    Optional<DeskColumn> findByPrevAndDesk(Integer prev, Desk desk);
    Optional<DeskColumn> findByNextAndDesk(Integer next, Desk desk);

    List<DeskColumn> findAllByDesk(Desk desk);
    List<DeskColumn> findAllByDeskOrderById(Desk desk);
}
