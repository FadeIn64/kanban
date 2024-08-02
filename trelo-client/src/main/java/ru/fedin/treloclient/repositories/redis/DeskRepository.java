package ru.fedin.treloclient.repositories.redis;

import org.springframework.data.repository.CrudRepository;
import ru.fedin.treloclient.dtos.response.DeskRes;

public interface DeskRepository extends CrudRepository<DeskRes, Integer> {
}
