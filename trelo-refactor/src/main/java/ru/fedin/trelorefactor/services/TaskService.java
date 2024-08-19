package ru.fedin.trelorefactor.services;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.HistoryDto;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.History;
import ru.fedin.trelorefactor.eintites.Task;
import ru.fedin.trelorefactor.exceptions.EntityNotFoundException;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.entities.HistoryMapper;
import ru.fedin.trelorefactor.mappers.entities.TaskMapper;
import ru.fedin.trelorefactor.mappers.entities.UserMapper;
import ru.fedin.trelorefactor.repositories.jpa.ColumnEntityRepository;
import ru.fedin.trelorefactor.repositories.jpa.DeskRepository;
import ru.fedin.trelorefactor.repositories.jpa.HistoryRepository;
import ru.fedin.trelorefactor.repositories.jpa.TaskRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final HistoryRepository historyRepository;
    private final HistoryMapper historyMapper;

    private final ColumnEntityRepository columnRepository;
    private final DeskRepository deskRepository;
    private final UserMapper userMapper;

    public TaskDto findById(long taskId) {
        return taskMapper.toDto(taskRepository.findById(taskId)
                .orElseThrow(()-> new EntityNotFoundException("task don't exist")));
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

    @Transactional
    public void removeTask(Long taskId) {
        try {
            taskRepository.deleteById(taskId);
        }catch (Exception e) {
            throw new ModifyDataException(e.getCause());
        }
    }

    @Transactional
    public TaskDto change(@Valid TaskDto taskDto) {
        Task task = taskMapper.toEntity(taskDto);
        Task reference = taskRepository.findById(task.getId()).orElseThrow(()-> new ModifyDataException("task don't exist"));

        //Данные которые нельзя обновить просто так
        task.setCreateDate(reference.getCreateDate());
        task.setDeskId(reference.getDeskId());
        task.setColumnId(reference.getColumnId());
        task.setUserId(reference.getUserId());

        return taskMapper.toDto(taskRepository.save(task));
    }

    public List<HistoryDto> findAllHistoryByTaskAndChangeDate(Long taskId, LocalDateTime from, LocalDateTime to) {
        if (!taskRepository.existsById(taskId)) {
            throw new EntityNotFoundException("task don't exist");
        }
        return historyMapper.toDto(historyRepository.findAllByTaskAndChangeDateBetween(Task.builder().id(taskId).build(), from, to));
    }

    @Transactional
    public UserDto changePerformer(long taskId, Long performer) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new EntityNotFoundException("task don't exist"));
        if (performer != null // для более удобной очистки ползователя
                && deskRepository.existsContributor(task.getDeskId(), performer) < 1) {
            throw new ModifyDataException("performer is not a contributor");
        }
        task.setUserId(performer);
        addHistory(task);
        task = taskRepository.save(task);
        return userMapper.toDto(task.getPerformer());
    }

    public void changeColumn(Long taskId, Long columnId) {
        Task task = taskRepository.findById(taskId).orElseThrow(()-> new EntityNotFoundException("task don't exist"));
        if (!columnRepository.existsByIdAndDeskId(columnId, task.getDeskId())) {
            throw new ModifyDataException("column is not in desk");
        }

        task.setColumnId(columnId);
        addHistory(task);
        task = taskRepository.save(task);
    }
}
