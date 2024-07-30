package ru.fedin.treloclient.services;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.requests.TaskPerformerReq;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final HistoryService historyService;


    @Transactional
    public DeskTaskReq findById(int id){

        return DeskTaskReq.builder().id(id).build();
    }

    @Transactional
    public DeskTaskReq create(DeskTaskReq dto){

        return  dto;
    }

    @Transactional
    public boolean changeColumn(Integer taskId, Integer newColumn){

        return true;
    }

    @Transactional
    public DeskTaskReq change(DeskTaskReq dto){



        return dto;
    }

    @Transactional
    public List<TaskPerformerReq> addPerformer(Integer taskId, String newContributor){

        return new ArrayList<>();
    }

    @Transactional
    public List<TaskPerformerReq> removePerformer(Integer taskId, @NotNull String newContributor){

        return new ArrayList<>();
    }

//    @Transactional
    public boolean removeTask(Integer id){
        try {
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
