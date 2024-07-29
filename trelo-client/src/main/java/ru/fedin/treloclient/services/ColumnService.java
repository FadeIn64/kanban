package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.DeskColumnDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ColumnService {




    public DeskColumnDTO findById(Integer id){

        return DeskColumnDTO.builder().id(id).build();
    }

    @Transactional
    public DeskColumnDTO create(DeskColumnDTO dto){

        return dto;
    }

    @Transactional
    public DeskColumnDTO rename(Integer id, String newName){
       return DeskColumnDTO.builder().id(id).build();
    }

    @Transactional
    public List<DeskColumnDTO> move(Integer id, int offset){

        return new ArrayList<>();

    }



    public boolean removeColumn(Integer id){

        return true;
    }
}
