package ru.fedin.trelo.services;

import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.dtos.TaskPerformerDTO;
import ru.fedin.trelo.eintites.DeskTask;
import ru.fedin.trelo.eintites.TaskPerformer;
import ru.fedin.trelo.mappers.PerformerMapper;
import ru.fedin.trelo.mappers.TaskMapper;
import ru.fedin.trelo.repositories.jpa.DeskColumnRepository;
import ru.fedin.trelo.repositories.jpa.DeskContributorRepository;
import ru.fedin.trelo.repositories.jpa.DeskTaskRepository;
import ru.fedin.trelo.repositories.jpa.TaskPerformerRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final DeskTaskRepository taskRepository;
    private final TaskMapper taskMapper;

    private final TaskPerformerRepository performerRepository;
    private final DeskContributorRepository contributorRepository;
    private final PerformerMapper performerMapper;

    private final DeskColumnRepository columnRepository;

    private final HistoryService historyService;

    private static final DeskTask EMPTY = DeskTask.builder().id(0).build();

    @Transactional
    public DeskTaskDTO findById(int id){
        var opt = taskRepository.findById(id);
        var task = opt.orElse(EMPTY);
        return taskMapper.toDto(task);
    }

    @Transactional
    public DeskTaskDTO create(DeskTaskDTO dto){
        System.out.println(dto.getCreateDate());
        var entity = taskMapper.toEntity(dto);
        entity.setCreateDate(LocalDateTime.now());
        entity = taskRepository.save(entity);

        var colOpt = columnRepository.findByPrevAndDesk(null, dto.getDesk());

        if(colOpt.isPresent())
            entity = historyService.changeColumn(entity, colOpt.get().getId());

        dto = taskMapper.toDto(entity);
        return  dto;
    }

    @Transactional
    public boolean changeColumn(Integer taskId, Integer newColumn){
        var opt = taskRepository.findById(taskId);
        if (opt.isEmpty())
            return false;
        var entity = opt.get();
        entity = historyService.changeColumn(entity, newColumn);
        return Objects.equals(entity.getColumn(), newColumn);
    }

    @Transactional
    public DeskTaskDTO change(DeskTaskDTO dto){

        var opt = taskRepository.findById(dto.getId());
        if (opt.isEmpty())
            return taskMapper.toDto(EMPTY);

        if (!opt.get().getDesk().equals(dto.getDesk()))
            return taskMapper.toDto(EMPTY);

        var entity = DeskTask.builder()
                .id(dto.getId())
                .desk(opt.get().getDesk())
                .column(opt.get().getColumn())
                .header(dto.getHeader())
                .description(dto.getDescription())
                .author(opt.get().getAuthor())
                .performers(opt.get().getPerformers())
                .importance(dto.getImportance())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .createDate(dto.getCreateDate())
                .coast(dto.getCoast())
                .file(opt.get().getFile())
                .build();

        entity = taskRepository.save(entity);

        return taskMapper.toDto(entity);
    }

    @Transactional
    public List<TaskPerformerDTO> addPerformer(Integer taskId, String newContributor){
        var opt = taskRepository.findById(taskId);
        if (opt.isEmpty())
            return new ArrayList<>();
        var task = opt.get();

        var opt_contr = contributorRepository.findByDeskAndContributor(task.getDesk(), newContributor);
        if (opt_contr.isEmpty())
            return new ArrayList<>();

        var contributor = opt_contr.get();

        var performer = TaskPerformer.builder().task(taskId).contributor(contributor).build();
        performer = performerRepository.save(performer);

        task.getPerformers().add(performer);
        return performerMapper.toDto(task.getPerformers());
    }

    @Transactional
    public List<TaskPerformerDTO> removePerformer(Integer taskId, @NotNull String newContributor){
        var opt = taskRepository.findById(taskId);
        if (opt.isEmpty())
            return new ArrayList<>();
        var task = opt.get();

        var performer = task.getPerformers().stream()
                .filter(p -> newContributor.equals(p.getContributor().getContributor()))
                .toList();

        performerRepository.deleteAll(performer);

        task.getPerformers().removeAll(performer);
        return performerMapper.toDto(task.getPerformers());
    }

//    @Transactional
    public boolean removeTask(Integer id){
        try {
            taskRepository.deleteById(id);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

}
