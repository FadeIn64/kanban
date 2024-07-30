package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.requests.TaskHistoryReq;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {


    @Transactional
    public DeskTaskReq changeColumn(DeskTaskReq task, Integer newColumnId){

        return task;
    }

    @Transactional
    public List<TaskHistoryReq> findAllByTask(Integer taskId){
        return new ArrayList<>();
    }

    @Transactional
    public List<TaskHistoryReq> findAllByTaskAndChangeDate(Integer taskId,
                                                           LocalDateTime changeDate,
                                                           LocalDateTime changeDate2){
        return new ArrayList<>();
    }
}
