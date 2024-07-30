package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {




    public DeskColumnReq findById(Integer id){

        return DeskColumnReq.builder().id(id).build();
    }

    @Transactional
    public DeskColumnReq create(DeskColumnReq dto){

        return dto;
    }

    @Transactional
    public DeskColumnReq rename(Integer id, String newName){
       return DeskColumnReq.builder().id(id).build();
    }

    @Transactional
    public List<DeskColumnReq> move(Integer id, int offset){

        return new ArrayList<>();

    }



    public boolean removeColumn(Integer id){

        return true;
    }
}
