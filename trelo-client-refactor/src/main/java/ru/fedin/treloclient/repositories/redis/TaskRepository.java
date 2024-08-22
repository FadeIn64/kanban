package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.fedin.treloclient.models.TaskModel;

public interface TaskRepository extends KeyValueRepository<TaskModel, Long> {
}
