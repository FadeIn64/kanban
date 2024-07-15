package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.DeskTask;

public interface DeskTaskRepository extends DeleteModifyJpaRepository<DeskTask, Integer> {
}
