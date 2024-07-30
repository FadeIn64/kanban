package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColumnService {


    private final RestClient restClient;



    public DeskColumnRes findById(Integer id){

        var res = restClient
                .get()
                .uri("/column/" + id)
                .retrieve()
                .toEntity(DeskColumnRes.class);

        var entity = res.getBody();

        return entity;
    }


    public DeskColumnReq create(DeskColumnReq dto){

        return dto;
    }


    public DeskColumnReq rename(Integer id, String newName){
       return DeskColumnReq.builder().id(id).build();
    }


    public List<DeskColumnReq> move(Integer id, int offset){

        return new ArrayList<>();

    }



    public boolean removeColumn(Integer id){

        return true;
    }
}
