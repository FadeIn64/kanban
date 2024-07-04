package ru.fedin.deskswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.deskswagger.schema.DeskHistory;
import ru.fedin.deskswagger.schema.DeskTask;
import ru.fedin.deskswagger.schema.Importance;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/task")
@Tag(name = "Задача", description = "Работа с задачами")
public class TaskController {

    @Operation(summary = "Найти задачу по id", description = "Возвращает всю информацию о задаче")
    @GetMapping("/{taskId}")
    ResponseEntity getTask(@PathVariable int taskId){
        return new ResponseEntity<>(DeskTask.DEFAULT.get(0), HttpStatus.OK);
    }

    @Operation(summary = "Создать задачу")
    @PostMapping
    ResponseEntity createTask(@RequestBody TaskCreate task){
        return new ResponseEntity<>(
                new DeskTask(1, task.deskId, task.columnId, task.header, task.description, task.author, new ArrayList<>(),
                        task.importance, Calendar.getInstance().getTime(), task.startDate, task.endDate, task.coast, task.file),
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
    @PutMapping
    ResponseEntity changeTask(@RequestBody TaskChange task){
        return new ResponseEntity<>(
                new DeskTask(task.id, 1, 1, task.header, task.description, "author", task.performers,
                        task.importance, Calendar.getInstance().getTime(), task.startDate, task.endDate, task.coast, task.file),
                HttpStatus.OK
        );
    }

    @Operation(summary = "Добавить Исполнителя",
            description = "Возвращает список исполнителей")
    @PostMapping("/{taskId}/performer")
    ResponseEntity addPerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Новый исполнитель") String performer){
        return new ResponseEntity<>(DeskTask.DEFAULT.get(0).getPerformers(), HttpStatus.CREATED);
    }

    @Operation(summary = "Удалить Исполнителя",
            description = "Возвращает список исполнителей")
    @DeleteMapping("/{taskId}/performer")
    ResponseEntity removePerformer(@PathVariable int taskId,
                                @RequestBody
                                @Parameter(description = "Исполнитель") String performer){
        return new ResponseEntity<>(DeskTask.DEFAULT.get(0).getPerformers(), HttpStatus.ACCEPTED);
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
        return new ResponseEntity<>(DeskHistory.DEFAULT, HttpStatus.OK);
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor

    static class TaskCreate{
        @Schema(description = "Индификатор доски")
        Integer deskId;
        @Schema(description = "Индификатор колонки")
        Integer columnId;
        @Schema(description = "Загаловок задачи")
        String header;
        @Schema(description = "Описание задачи")
        String description;
        @Schema(description = "Автор")
        String author;
        @Schema(description = "Важность задачи", example = "HIGH")
        Importance importance;
        @Schema(description = "Дата начала задачи")
        Date startDate;
        @Schema(description = "Дата окончания задачи")
        Date endDate;
        @Schema(description = "Стоимость задачи")
        double coast;
        @Schema(description = "Прикрепленный файл. Пока не поддерживается")
        String file;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class TaskChange{
        Integer id;
        @Schema(description = "Загаловок задачи")
        String header;
        @Schema(description = "Описание задачи")
        String description;
        @Schema(description = "Ответсвенные за выполнение задачи")
        List<String> performers;
        @Schema(description = "Важность задачи", example = "Medium")
        Importance importance;
        @Schema(description = "Дата начала задачи")
        Date startDate;
        @Schema(description = "Дата окончания задачи")
        Date endDate;
        @Schema(description = "Стоимость задачи")
        double coast;
        @Schema(description = "Прикрепленный файл. Пока не поддерживается")
        String file;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @Schema(name = "History")
    static class HistoryReq{
        @Schema(description = "Индефикатор задачи")
        Integer id;
        @Schema(description = "Дата начала периода")
        Date from;
        @Schema(description = "Дата окончания периода")
        Date to;
    }
}
