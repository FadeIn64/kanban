package ru.fedin.trelorefactor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fedin.trelorefactor.eintites.ColumnEntity;
import ru.fedin.trelorefactor.eintites.Desk;
import ru.fedin.trelorefactor.repositories.jpa.ColumnEntityRepository;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultColumnCreatorImpl implements DefaultColumnCreator{
    private final ColumnEntityRepository columnRepository;


    @Override
    public List<ColumnEntity> createDefault(Desk desk) {
        List<ColumnEntity> defaults = new ArrayList<>();
        defaults.add(ColumnEntity.builder().desk(desk).order(1).name("Todo").build());
        defaults.add(ColumnEntity.builder().desk(desk).order(2).name("Working").build());
        defaults.add(ColumnEntity.builder().desk(desk).order(3).name("Finished").build());

        return columnRepository.saveAll(defaults);
    }
}
