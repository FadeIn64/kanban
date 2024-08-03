package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.fedin.trelo.eintites.DeskTask;

import java.util.List;

public interface DeskTaskRepository extends DeleteModifyJpaRepository<DeskTask, Integer>, JpaSpecificationExecutor<DeskTask> {

    List<DeskTask> findAllByColumn(Integer column);
}
