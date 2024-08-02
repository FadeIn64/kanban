package ru.fedin.treloclient.cache;

import java.util.Optional;

public interface CacheService<T, ID> {
    T save(T obj);
    Optional<T> findById(ID id);

    void delete(ID id);
}
