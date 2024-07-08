package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskContributor;

import java.util.List;
import java.util.Optional;

public interface DeskContributorRepository extends JpaRepository<DeskContributor, Integer> {
    Optional<DeskContributor> findByDeskAndContributor(Integer desk, @NotNull String contributor);

    List<DeskContributor> findAllByDesk(Integer desk);
}
