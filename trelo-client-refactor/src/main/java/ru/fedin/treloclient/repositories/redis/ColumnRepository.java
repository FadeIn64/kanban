package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.keyvalue.repository.KeyValueRepository;
import ru.fedin.treloclient.models.ColumnModel;

public interface ColumnRepository extends KeyValueRepository<ColumnModel, Long> {
}
