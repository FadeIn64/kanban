package ru.fedin.treloclient.services;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.DeskTaskDTO;
import ru.fedin.treloclient.dtos.TaskPerformerDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final HistoryService historyService;


    @Transactional
    public DeskTaskDTO findById(int id){

        return DeskTaskDTO.builder().id(id).build();
    }

    @Transactional
    public DeskTaskDTO create(DeskTaskDTO dto){

        return  dto;
    }

    @Transactional
    public boolean changeColumn(Integer taskId, Integer newColumn){

        return true;
    }

    @Transactional
    public DeskTaskDTO change(DeskTaskDTO dto){



        return dto;
    }

    @Transactional
    public List<TaskPerformerDTO> addPerformer(Integer taskId, String newContributor){

        return new ArrayList<>();
    }

    @Transactional
    public List<TaskPerformerDTO> removePerformer(Integer taskId, @NotNull String newContributor){

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
