package ru.fedin.trelo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelo.dtos.DeskTaskDTO;
import ru.fedin.trelo.dtos.TaskHistoryDTO;
import ru.fedin.trelo.dtos.TaskPerformerDTO;

import java.util.Date;

@RestController
@RequestMapping("/task")
@Tag(name = "Задача", description = "Работа с задачами")
public class TaskController {

    @Operation(summary = "Найти задачу по id", description = "Возвращает всю информацию о задаче")
    @GetMapping("/{taskId}")
    ResponseEntity getTask(@PathVariable int taskId){
        return new ResponseEntity<>(new DeskTaskDTO(), HttpStatus.OK);
    }

    @Operation(summary = "Создать задачу")
    @PostMapping
    ResponseEntity createTask(@RequestBody DeskTaskDTO task){
        return new ResponseEntity<>(
                task,
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Удалить задачу",
            description = "Удаляет всё, включая историю")
    @DeleteMapping("/{taskId}")
    HttpStatus deleteTask(@PathVariable int taskId){
        return HttpStatus.OK;
    }

    @Operation(summary = "Редактировать задачу")
    @PutMapping("/{taskId}")
    ResponseEntity changeTask(@RequestBody DeskTaskDTO task, @PathVariable String taskId){
        return new ResponseEntity<>(
                task,
                HttpStatus.OK
        );
    }

    @Operation(summary = "Добавить Исполнителя",
            description = "Возвращает список исполнителей")
    @PostMapping("/{taskId}/performer")
    ResponseEntity addPerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Новый исполнитель") String performer){
        return new ResponseEntity<>(new TaskPerformerDTO(), HttpStatus.CREATED);
    }

    @Operation(summary = "Удалить Исполнителя",
            description = "Возвращает список исполнителей")
    @DeleteMapping("/{taskId}/performer")
    ResponseEntity removePerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Исполнитель") String performer){
        return new ResponseEntity<>(new DeskTaskDTO(), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Изменить колнку для задачи")
    @PutMapping("/{taskId}/changeColumn")
    HttpStatus changeColumn(@PathVariable int taskId,
                            @RequestParam
                            @Parameter(description = "Id колонки")
                            String columnId){
        return HttpStatus.ACCEPTED;
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
            Date from,
            @RequestParam (defaultValue = "2100-01-01T01:30:00.000-05:00")
            @Parameter(description = "Дата окончания периода", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            Date to

    ){
        return new ResponseEntity<>(new TaskHistoryDTO(), HttpStatus.OK);
    }

}
