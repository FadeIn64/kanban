package ru.fedin.treloclient.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.DeskContributorDTO;
import ru.fedin.treloclient.dtos.DeskDTO;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeskService {


    @Transactional
    public DeskDTO findById(int id){
        return DeskDTO.builder().id(id).build();
    }

    @Transactional
    public DeskDTO create(DeskDTO dto){

        return dto;
    }

    @Transactional
    public void delete(Integer id){

    }

    @Transactional
    public DeskDTO rename(Integer deskId, String newName){

        return DeskDTO.builder().id(deskId).build();
    }

    @Transactional
    public List<DeskContributorDTO> addContributor(int deskId, String user){

        return new ArrayList<>();
    }

    @Transactional
    public boolean removeContributor(int deskId, String user){

        return true;
    }

}
