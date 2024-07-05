package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskColumn;

public interface DeskColumnRepository extends JpaRepository<DeskColumn, Integer> {
}
