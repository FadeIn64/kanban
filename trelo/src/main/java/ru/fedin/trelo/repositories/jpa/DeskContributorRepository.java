package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskContributor;

public interface DeskContributorRepository extends JpaRepository<DeskContributor, Integer> {
}
