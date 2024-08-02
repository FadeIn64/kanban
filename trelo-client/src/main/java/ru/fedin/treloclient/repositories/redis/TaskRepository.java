package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;

public interface TaskRepository extends CrudRepository<DeskTaskRes, Integer> {
}
