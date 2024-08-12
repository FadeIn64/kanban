package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.fedin.trelorefactor.eintites.UsersCredentialsData;

import java.util.Optional;

public interface UsersCredentialsDataRepository extends JpaRepository<UsersCredentialsData, Long> {

    @Query("""
        select ucd from UsersCredentialsData ucd
        where ucd.username = :username
    """)
    Optional<UsersCredentialsData> findByUsername(String username);

}