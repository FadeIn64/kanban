package ru.fedin.treloclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import ru.fedin.treloclient.dtos.HistoryDto;
import ru.fedin.treloclient.dtos.TaskDto;
import ru.fedin.treloclient.services.TaskService;


import java.time.LocalDateTime;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/task")
@Tag(name = "Задача", description = "Работа с задачами")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskService taskService;

    @Operation(summary = "Найти задачу по id", description = "Возвращает всю информацию о задаче")
    @GetMapping("/{taskId}")
    @ResponseBody
    public TaskDto getTask(@PathVariable long taskId){
        return taskService.findById(taskId);
    }

    @Operation(summary = "Создать задачу")
    @PostMapping("/{deskId}/{columnId}")
    @ResponseStatus(CREATED)
    @ResponseBody
    public void createTask(@RequestBody @Valid TaskDto task, @PathVariable long deskId, @PathVariable long columnId){
        task.setDeskId(deskId);
        task.setColumnId(columnId);
        taskService.create(task);

    }

    @Operation(summary = "Удалить задачу",
            description = "Удаляет всё, включая историю")
    @DeleteMapping("/{taskId}")
    @ResponseStatus(ACCEPTED)
    public void deleteTask(@PathVariable Long taskId){
        taskService.removeTask(taskId);
    }

    @Operation(summary = "Редактировать задачу")
    @PutMapping("/{taskId}")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    public void changeTask(@RequestBody @Valid TaskDto task, @PathVariable Long taskId){
        task.setId(taskId);
        taskService.change(task);

    }

    @Operation(summary = "Добавить Исполнителя",
            description = "Возвращает список исполнителей")
    @PostMapping("/{taskId}/performer")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    public void changePerformer(@PathVariable long taskId,
                                   @RequestBody
                                @Parameter(description = "Новый исполнитель") Long performer){
        taskService.changePerformer(taskId, performer);
    }

    @Operation(summary = "Удалить Исполнителя",
            description = "Возвращает список исполнителей")
    @DeleteMapping("/{taskId}/performer")
    @ResponseStatus(ACCEPTED)
    public void removePerformer(@PathVariable long taskId){
        taskService.changePerformer(taskId, null);
    }

    @Operation(summary = "Изменить колнку для задачи")
    @PutMapping("/{taskId}/changeColumn")
    void changeColumn(@PathVariable Long taskId,
                            @RequestBody
                            @Parameter(description = "Id колонки")
                            Long columnId){

        taskService.changeColumn(taskId, columnId);
    }

//    @PostMapping("/search")
//    Page<DeskTaskDTO> search(@RequestBody SearchRequest request){
//        log.info("Search: {}", request);
//        return taskService.search(request);
//    }

//    @Operation(summary = "История перемещения задачи по колонкам")
//    @GetMapping("/{taskId}/history")
//    @ResponseBody
//    @ResponseStatus(OK)
//    public List<HistoryDto> getHistory(
//            @PathVariable
//            @Parameter(description = "Индефикатор задачи")
//            Long taskId,
//            @RequestParam (defaultValue = "2020-01-01T01:30:00.000-05:00")
//            @Parameter(description = "Дата начала периода", required = false)
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//            LocalDateTime from,
//            @RequestParam (defaultValue = "2100-01-01T01:30:00.000-05:00")
//            @Parameter(description = "Дата окончания периода", required = false)
//            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//            LocalDateTime to
//
//    ){
//        return taskService.findAllHistoryByTaskAndChangeDate(taskId, from, to);
//    }

}
