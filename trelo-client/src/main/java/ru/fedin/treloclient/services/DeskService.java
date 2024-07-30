package ru.fedin.treloclient.services;


import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.DeskContributorReq;
import ru.fedin.treloclient.dtos.requests.DeskReq;
import ru.fedin.treloclient.dtos.response.DeskRes;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeskService {

    private final RestClient restClient;
    private final KafkaTemplate<UUID, DeskReq> deskTemplate;
    @Value("${kafka.topic.desk}")
    private String deskTopic;



    public DeskRes findById(int id){
        ResponseEntity<DeskRes> res = restClient.get()
                .uri("/desk/"+id).retrieve().toEntity(DeskRes.class);
        var entity = res.getBody();
        return entity;
    }


    public DeskReq create(DeskReq dto){
        var uuid = Generators.timeBasedGenerator().generate();
        deskTemplate.send(deskTopic, uuid, dto);
        return dto;
    }


    public void delete(Integer id){

    }


    public DeskReq rename(Integer deskId, String newName){

        return DeskReq.builder().id(deskId).build();
    }


    public List<DeskContributorReq> addContributor(int deskId, String user){

        return new ArrayList<>();
    }


    public boolean removeContributor(int deskId, String user){

        return true;
    }

}
