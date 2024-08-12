package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.Desk;

public interface DeskRepository extends JpaRepository<Desk, Long> {
}