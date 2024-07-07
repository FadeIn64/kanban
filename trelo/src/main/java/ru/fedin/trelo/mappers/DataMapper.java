package ru.fedin.trelo.mappers;

import java.util.Collection;

public interface DataMapper<E, D> {

    E toEntity(D dto);
    D toDto(E entity);

    Collection<E> toEntity(Collection<D> dtos);
    Collection<D> toDto(Collection<E> entities);
}
