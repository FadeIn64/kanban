package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.fedin.treloclient.models.DeskModel;

public interface DeskRepository extends KeyValueRepository<DeskModel, Long> {
}
