package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.fedin.trelorefactor.eintites.Desk;

public interface DeskRepository extends JpaRepository<Desk, Long> {

    @Modifying
    @Query("""
        update Desk d
        set d.name = :name
        where d.id = :id
    """)
    Integer updateNameById(Long id, String name);


}