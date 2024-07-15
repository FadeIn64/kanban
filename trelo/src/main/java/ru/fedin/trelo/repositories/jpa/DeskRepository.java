package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fedin.trelo.eintites.Desk;

public interface DeskRepository extends DeleteModifyJpaRepository<Desk, Integer> {


}
