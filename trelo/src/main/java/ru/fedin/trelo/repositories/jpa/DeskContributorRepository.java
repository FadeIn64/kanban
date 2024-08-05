package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import ru.fedin.trelo.eintites.DeskContributor;

import java.util.List;
import java.util.Optional;

public interface DeskContributorRepository extends DeleteModifyJpaRepository<DeskContributor, Integer> {
    Optional<DeskContributor> findByDeskAndContributor(Integer desk, @NotNull String contributor);

    List<DeskContributor> findAllByDesk(Integer desk);
}
