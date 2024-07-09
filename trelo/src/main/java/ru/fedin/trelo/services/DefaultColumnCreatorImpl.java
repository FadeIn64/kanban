package ru.fedin.trelo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fedin.trelo.eintites.DeskColumn;
import ru.fedin.trelo.repositories.jpa.DeskColumnRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultColumnCreatorImpl implements DefaultColumnCreator{
    private final DeskColumnRepository deskColumnRepository;


    @Override
    public List<DeskColumn> createDefault(int deskId) {
        List<DeskColumn> defaults = new ArrayList<>();
        defaults.add(DeskColumn.builder().desk(deskId).name("Todo").build());
        defaults.add(DeskColumn.builder().desk(deskId).name("Working").build());
        defaults.add(DeskColumn.builder().desk(deskId).name("Finished").build());

        defaults = deskColumnRepository.saveAll(defaults);

        defaults.get(0).setNext(defaults.get(1).getId());

        defaults.get(1).setNext(defaults.get(2).getId());
        defaults.get(1).setPrev(defaults.get(0).getId());

        defaults.get(2).setPrev(defaults.get(1).getId());

        return deskColumnRepository.saveAll(defaults);
    }
}
