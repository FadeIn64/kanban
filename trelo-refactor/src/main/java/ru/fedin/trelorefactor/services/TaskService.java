package ru.fedin.trelorefactor.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.HistoryDto;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.eintites.History;
import ru.fedin.trelorefactor.eintites.Task;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.HistoryMapper;
import ru.fedin.trelorefactor.mappers.TaskMapper;
import ru.fedin.trelorefactor.repositories.jpa.ColumnEntityRepository;
import ru.fedin.trelorefactor.repositories.jpa.HistoryRepository;
import ru.fedin.trelorefactor.repositories.jpa.TaskRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    private final ColumnEntityRepository columnRepository;

    public TaskDto findById(long taskId) {
        return taskMapper.toDto(taskRepository.findById(taskId)
                .orElseThrow(()-> new EntityNotFound("task don't exist")));
    }

    @Transactional
    public TaskDto create(@Valid TaskDto task) {

        if (!columnRepository.existsByIdAndDeskId(task.getColumnId(), task.getDeskId())) {
            throw new ModifyDataException("column don't exist in desk");
        }
        task.setCreateDate(LocalDateTime.now());
        Task entity = taskMapper.toEntity(task);
        try {
            entity = taskRepository.save(entity);
            this.addHistory(entity);
        } catch (Exception e) {
            throw new ModifyDataException(e.getMessage(), e.getCause());
        }
        return taskMapper.toDto(entity);
    }

    @Transactional
    public HistoryDto addHistory(Task task) {
        History history = History.builder()
                .id(0L)
                .taskId(task.getId())
                .columnId(task.getColumnId())
                .userId(task.getUserId())
                .changeDate(LocalDateTime.now())
                .build();
        return historyMapper.toDto(historyRepository.save(history));
    }
}
