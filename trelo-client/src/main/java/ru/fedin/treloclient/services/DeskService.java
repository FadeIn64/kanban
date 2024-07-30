package ru.fedin.treloclient.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.DeskContributorReq;
import ru.fedin.treloclient.dtos.requests.DeskReq;


import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeskService {

    private final RestClient restClient;


    public DeskReq findById(int id){
        DeskReq req = restClient.get()
                .uri("/desk/"+id).retrieve()
                .body(DeskReq.class);
        return req;
    }

    @Transactional
    public DeskReq create(DeskReq dto){

        return dto;
    }

    @Transactional
    public void delete(Integer id){

    }

    @Transactional
    public DeskReq rename(Integer deskId, String newName){

        return DeskReq.builder().id(deskId).build();
    }

    @Transactional
    public List<DeskContributorReq> addContributor(int deskId, String user){

        return new ArrayList<>();
    }

    @Transactional
    public boolean removeContributor(int deskId, String user){

        return true;
    }

}
