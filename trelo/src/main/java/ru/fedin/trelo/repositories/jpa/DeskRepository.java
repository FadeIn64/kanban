package ru.fedin.trelo.repositories.jpa;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.Query;
import ru.fedin.trelo.eintites.Desk;

import java.util.Optional;

public interface DeskRepository extends DeleteModifyJpaRepository<Desk, Integer> {

    @Query("""
        select d from Desk d
        where d.id = :id
    """)
    Optional<Desk> findById(@NotNull Integer id);

}
