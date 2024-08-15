package ru.fedin.trelorefactor.services;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.eintites.ColumnEntity;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.ColumnMapper;
import ru.fedin.trelorefactor.repositories.jpa.ColumnEntityRepository;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final ColumnEntityRepository columnRepository;
    private final ColumnMapper columnMapper;

    public ColumnDto findById(long columnId) {
        return columnMapper.toDto(columnRepository.findById(columnId)
                .orElseThrow(()->new EntityNotFound("column don't exist")));
    }

    @Transactional
    public ColumnDto create(ColumnDto column, long deskId)
    {
        ColumnEntity columnEntity = columnMapper.toEntity(column);
        columnEntity.setDeskId(deskId);
        try {
            return columnMapper.toDto(columnRepository.save(columnEntity));
        }
        catch (Exception e) {
            throw new ModifyDataException(e.getMessage(), e.getCause());
        }
    }

    public void remove(long columnId) {
        try {
            columnRepository.deleteById(columnId);
        }
        catch (Exception e) {
            throw new ModifyDataException(e.getMessage(), e.getCause());
        }
    }
}
