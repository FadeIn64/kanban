package ru.fedin.trelorefactor.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fedin.trelorefactor.eintites.UsersCredentialsData;

public interface UsersCredentialsDataRepository extends JpaRepository<UsersCredentialsData, Long> {
}