package ru.fedin.treloclient.services;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.requests.TaskPerformerReq;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RestClient restClient;
    private final HistoryService historyService;



    public DeskTaskRes findById(int id){

        var res = restClient.
                get()
                .uri("/task/"+id)
                .retrieve()
                .toEntity(DeskTaskRes.class);

        var entity = res.getBody();

        return entity;
    }


    public DeskTaskReq create(DeskTaskReq dto){

        return  dto;
    }


    public boolean changeColumn(Integer taskId, Integer newColumn){

        return true;
    }


    public DeskTaskReq change(DeskTaskReq dto){



        return dto;
    }


    public List<TaskPerformerReq> addPerformer(Integer taskId, String newContributor){

        return new ArrayList<>();
    }

    public List<TaskPerformerReq> removePerformer(Integer taskId, @NotNull String newContributor){

        return new ArrayList<>();
    }

    public boolean removeTask(Integer id){
        try {
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
