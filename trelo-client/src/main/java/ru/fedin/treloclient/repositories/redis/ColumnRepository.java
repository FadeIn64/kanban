package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

public interface ColumnRepository extends CrudRepository<DeskColumnRes, Integer> {
}
