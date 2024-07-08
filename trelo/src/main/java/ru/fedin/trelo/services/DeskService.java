package ru.fedin.trelo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.dtos.DeskDTO;
import ru.fedin.trelo.eintites.Desk;
import ru.fedin.trelo.eintites.DeskContributor;
import ru.fedin.trelo.mappers.ContributorMapper;
import ru.fedin.trelo.mappers.DeskMapper;
import ru.fedin.trelo.repositories.jpa.DeskContributorRepository;
import ru.fedin.trelo.repositories.jpa.DeskRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeskService {

    private static final Desk EMPTY = new Desk();
    static {
        EMPTY.setId(0);
    }

    private final DeskRepository deskRepository;
    private final DeskMapper deskMapper;
    private final DeskContributorRepository contributorRepository;
    private final ContributorMapper contributorMapper;


    public DeskDTO findById(int id){
        var opt = deskRepository.findById(id);
        var desk = opt.orElse(EMPTY);
        return deskMapper.toDto(desk);
    }

    public DeskDTO rename(Integer deskId, String newName){
        var opt = deskRepository.findById(deskId);
        if (opt.isEmpty())
            return deskMapper.toDto(EMPTY);
        var desk = opt.get();
        desk.setName(newName);
        desk = deskRepository.save(desk);
        return deskMapper.toDto(desk);
    }

    public List<DeskContributorDTO> addContributor(int deskId, String user){

        if (!deskRepository.existsById(deskId))
            return new ArrayList<>();

        var contributor = contributorRepository.findByDeskAndContributor(deskId, user);
        if (contributor.isEmpty()){
            var newContributor = new DeskContributor(0, deskId, user);
            contributorRepository.save(newContributor);
        }
        return contributorMapper.toDto(contributorRepository.findAllByDesk(deskId));
    }

    public boolean removeContributor(int deskId, String user){

        if (!deskRepository.existsById(deskId))
            return false;

        var contributor = contributorRepository.findByDeskAndContributor(deskId, user);
        contributor.ifPresent(contributorRepository::delete);
        return true;
    }

}
