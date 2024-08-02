package ru.fedin.treloclient.services;


import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.cache.DeskCacheService;
import ru.fedin.treloclient.dtos.requests.DeskContributorReq;
import ru.fedin.treloclient.dtos.requests.DeskReq;
import ru.fedin.treloclient.dtos.response.DeskContributorRes;
import ru.fedin.treloclient.dtos.response.DeskRes;
import ru.fedin.treloclient.mappers.DeskMapper;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeskService {

    private final RestClient restClient;
    private final KafkaTemplate<UUID, DeskReq> deskTemplate;
    private final DeskMapper mapper;
    @Value("${kafka.topic.desk}")
    private String deskTopic;
    private final DeskCacheService cacheService;


    public DeskRes findById(int id){
        var opt = cacheService.findById(id);
        if (opt.isPresent())
            return opt.get();
        try {
            ResponseEntity<DeskRes> res = restClient.get()
                    .uri("/desk/"+id).retrieve().toEntity(DeskRes.class);
            var entity = res.getBody();
            cacheService.save(entity);
            return entity;
        }
        catch (Exception e){
            return DeskRes.builder().id(0).build();
        }
    }


    public boolean create(DeskReq dto){

        if ("".equals(dto.getName()) || "".equals(dto.getAuthor()))
            return false;

        var uuid = Generators.timeBasedGenerator().generate();
        deskTemplate.send(deskTopic, uuid, dto);
        return true;
    }


    public boolean delete(Integer id){
        DeskRes desk = this.findById(id);

        if (desk.getId().equals(0))
            return false;

        desk.setName("");
        deskTemplate.send(deskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(desk));

        return true;
    }


    public boolean rename(Integer deskId, String newName){

        if ("".equals(newName))
            return false;

        DeskRes desk = this.findById(deskId);

        if (desk.getId().equals(0))
            return false;

        desk.setName(newName);
        deskTemplate.send(deskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(desk));

        return true;
    }


    public boolean addContributor(int deskId, String user){

        DeskRes desk = this.findById(deskId);

        if (desk.getId().equals(0))
            return false;

        desk.getDeskContributors().add(DeskContributorRes.builder().contributor(user).build());

        deskTemplate.send(deskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(desk));

        return true;
    }


    public boolean removeContributor(int deskId, String user){

        DeskRes desk = this.findById(deskId);

        if (desk.getId().equals(0))
            return false;

        desk.setDeskContributors(
                desk.getDeskContributors().stream()
                        .filter(c -> !c.getContributor().equals(user))
                        .toList()
        );

        deskTemplate.send(deskTopic, Generators.timeBasedGenerator().generate(), mapper.toReq(desk));

        return true;
    }

}
