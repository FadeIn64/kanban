package ru.fedin.trelo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelo.dtos.TaskHistoryDTO;
import ru.fedin.trelo.eintites.DeskTask;
import ru.fedin.trelo.eintites.TaskHistory;
import ru.fedin.trelo.mappers.TaskHistoryMapper;
import ru.fedin.trelo.repositories.jpa.DeskColumnRepository;
import ru.fedin.trelo.repositories.jpa.TaskHistoryRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final TaskHistoryRepository repository;
    private final TaskHistoryMapper mapper;

    private final DeskColumnRepository columnRepository;

    @Transactional
    public DeskTask changeColumn(DeskTask task, Integer newColumnId){

        var opt = columnRepository.findById(newColumnId);
        if (opt.isEmpty())
            return task;
        var newColumn = opt.get();
        var oldColumn =
                task.getColumn() == null?
                        null
                        : columnRepository.findById(task.getColumn()).get();

        var history = TaskHistory.builder().task(task).columnTo(newColumn).columnFrom(oldColumn).build();
        repository.save(history);

        task.setColumn(newColumnId);
        return task;
    }

    @Transactional
    public List<TaskHistoryDTO> findAllByTask(Integer taskId){
        var task = DeskTask.builder().id(taskId).build();

        var histories = repository.findAllByTask(task);
        return mapper.toDto(histories);
    }

    @Transactional
    public List<TaskHistoryDTO> findAllByTaskAndChangeDate(Integer taskId,
                                                           LocalDateTime changeDate,
                                                           LocalDateTime changeDate2){
        var task = DeskTask.builder().id(taskId).build();

        var histories = repository.findAllByTaskAndChangeDateBetween(task, changeDate, changeDate2);
        return mapper.toDto(histories);
    }
}
