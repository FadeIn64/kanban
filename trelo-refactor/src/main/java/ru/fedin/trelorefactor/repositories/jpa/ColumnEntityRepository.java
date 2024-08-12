package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.ColumnEntity;

public interface ColumnEntityRepository extends JpaRepository<ColumnEntity, Long> {
}