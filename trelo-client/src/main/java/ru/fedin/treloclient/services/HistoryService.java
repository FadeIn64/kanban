package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.DeskTaskDTO;
import ru.fedin.treloclient.dtos.TaskHistoryDTO;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {


    @Transactional
    public DeskTaskDTO changeColumn(DeskTaskDTO task, Integer newColumnId){

        return task;
    }

    @Transactional
    public List<TaskHistoryDTO> findAllByTask(Integer taskId){
        return new ArrayList<>();
    }

    @Transactional
    public List<TaskHistoryDTO> findAllByTaskAndChangeDate(Integer taskId,
                                                           LocalDateTime changeDate,
                                                           LocalDateTime changeDate2){
        return new ArrayList<>();
    }
}
