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


    @Modifying
    @Query(value = """
        insert into desks_users (desk_id, user_id)
        values (?, ?)
    """, nativeQuery = true)
    void addUser(long deskId, long userId);

    @Modifying
    @Query(value = """
    delete from desks_users
    where desk_id = ? and user_id = ?
    """, nativeQuery = true)
    void deleteUser(long deskId, long userId);

    @Query(value = "select count(*) from desks_users where desk_id = ? and user_id = ?",
            nativeQuery = true)
    Long existsContributor(long deskId, long userId);

}