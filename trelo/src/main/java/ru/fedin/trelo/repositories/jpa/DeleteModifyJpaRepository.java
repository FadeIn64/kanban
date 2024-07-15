package ru.fedin.trelo.repositories.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

@NoRepositoryBean
public interface DeleteModifyJpaRepository<E, U> extends JpaRepository<E, U> {
    @Query(value = "delete from #{#entityName} where id = :id", nativeQuery = true)
    @Modifying
    void removeById(@Param("id") Integer id);


}
