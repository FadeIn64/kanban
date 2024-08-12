package ru.fedin.trelorefactor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.eintites.Desk;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.UpdateOrInsertException;
import ru.fedin.trelorefactor.mappers.DeskMapper;
import ru.fedin.trelorefactor.repositories.jpa.DeskRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeskService {
    private final DeskRepository deskRepository;
    private final DeskMapper deskMapper;

    private final DefaultColumnCreator defaultColumnCreator;


    public DeskDto findById(long id){
        return deskMapper.toDto(deskRepository.findById(id).orElseThrow(() -> new EntityNotFound("desk not found")));
    }

    @Transactional
    public DeskDto create(DeskDto dto){
        Desk entity;
        try {
            entity = deskRepository.save(deskMapper.toEntity(dto));
        }
        catch (Exception e){
            log.error("Exception: ", e);
            throw new UpdateOrInsertException("desk don't create");
        }
        entity.setColumns(defaultColumnCreator.createDefault(entity));

        return deskMapper.toDto(entity);
    }
}
