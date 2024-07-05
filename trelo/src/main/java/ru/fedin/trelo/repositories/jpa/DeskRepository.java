package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.Desk;

public interface DeskRepository extends JpaRepository<Desk, Integer> {
}
