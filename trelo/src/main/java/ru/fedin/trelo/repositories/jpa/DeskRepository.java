package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.fedin.trelo.eintites.Desk;

public interface DeskRepository extends JpaRepository<Desk, Integer> {

    @Query(value = "delete from desk where id = :id returning 1", nativeQuery = true)
    void removeById(@Param("id") Integer id);

}
