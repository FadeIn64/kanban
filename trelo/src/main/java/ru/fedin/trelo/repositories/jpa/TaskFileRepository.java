package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelo.eintites.TaskFile;


public interface TaskFileRepository extends JpaRepository<TaskFile, Integer> {
}
