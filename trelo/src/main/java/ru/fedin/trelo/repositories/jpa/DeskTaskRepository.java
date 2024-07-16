package ru.fedin.trelo.repositories.jpa;

import ru.fedin.trelo.eintites.DeskTask;

import java.util.List;

public interface DeskTaskRepository extends DeleteModifyJpaRepository<DeskTask, Integer> {

    List<DeskTask> findAllByColumn(Integer column);
}
