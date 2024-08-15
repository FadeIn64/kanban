package ru.fedin.trelorefactor.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.eintites.ColumnEntity;
import ru.fedin.trelorefactor.mappers.ColumnMapper;
import ru.fedin.trelorefactor.repositories.jpa.ColumnEntityRepository;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnEntityRepository columnRepository;
    private final ColumnMapper columnMapper;

    public ColumnDto findById(long columnId) {
        return columnMapper.toDto(columnRepository.findById(columnId)
                .orElseThrow(()->new EntityNotFoundException("column don't exist")));
    }
}
