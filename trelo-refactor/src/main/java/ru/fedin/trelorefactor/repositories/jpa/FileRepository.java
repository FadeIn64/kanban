package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.File;

public interface FileRepository extends JpaRepository<File, Long> {
}