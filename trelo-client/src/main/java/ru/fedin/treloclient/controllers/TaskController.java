package ru.fedin.treloclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;
import ru.fedin.treloclient.dtos.response.MyPage;
import ru.fedin.treloclient.search.SearchRequest;
import ru.fedin.treloclient.services.HistoryService;
import ru.fedin.treloclient.services.TaskService;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/task")
@Tag(name = "Задача", description = "Работа с задачами")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final HistoryService historyService;

    @Operation(summary = "Найти задачу по id", description = "Возвращает всю информацию о задаче")
    @GetMapping("/{taskId}")
    ResponseEntity getTask(@PathVariable int taskId){
        var task = taskService.findById(taskId);
        if (task.getId() == 0)
            return new ResponseEntity<>(NOT_FOUND);
        return new ResponseEntity<>(task, OK);
    }

    @PostMapping("/search")
    MyPage<DeskTaskRes> search(@RequestBody SearchRequest request){
        return taskService.search(request);
    }

    @Operation(summary = "Создать задачу")
    @PostMapping
    ResponseEntity createTask(@RequestBody @Valid DeskTaskReq task){
        if (taskService.create(task))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Удалить задачу",
            description = "Удаляет всё, включая историю")
    @DeleteMapping("/{taskId}")
    ResponseEntity deleteTask(@PathVariable int taskId){
        if (taskService.removeTask(taskId))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Редактировать задачу")
    @PutMapping("/{taskId}")
    ResponseEntity changeTask(@RequestBody @Valid DeskTaskReq task, @PathVariable Integer taskId){
        task.setId(taskId);
        if (taskService.change(task))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Добавить Исполнителя",
            description = "Возвращает список исполнителей")
    @PostMapping("/{taskId}/performer")
    ResponseEntity addPerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Новый исполнитель") String performer){
        if (taskService.addPerformer(taskId, performer))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Удалить Исполнителя",
            description = "Возвращает список исполнителей")
    @DeleteMapping("/{taskId}/performer")
    ResponseEntity removePerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Исполнитель") String performer){
        if (taskService.removePerformer(taskId, performer))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Изменить колнку для задачи")
    @PutMapping("/{taskId}/changeColumn")
    ResponseEntity changeColumn(@PathVariable int taskId,
                            @RequestBody
                            @Parameter(description = "Id колонки")
                            Integer columnId){

        if (taskService.changeColumn(taskId, columnId))
            return new ResponseEntity<>(ACCEPTED);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "История перемещения задачи по колонкам")
    @GetMapping("/{taskId}/history")
    ResponseEntity getHistory(
            @PathVariable
            @Parameter(description = "Индефикатор задачи")
            Integer taskId,
            @RequestParam (defaultValue = "2020-01-01T01:30:00.000-05:00")
            @Parameter(description = "Дата начала периода", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,
            @RequestParam (defaultValue = "2100-01-01T01:30:00.000-05:00")
            @Parameter(description = "Дата окончания периода", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to

    ){
        var histories = historyService.findAllByTaskAndChangeDate(taskId, from, to);
        if (histories.isEmpty())
            return new ResponseEntity(BAD_REQUEST);
        return new ResponseEntity<>(histories, OK);
    }

}
