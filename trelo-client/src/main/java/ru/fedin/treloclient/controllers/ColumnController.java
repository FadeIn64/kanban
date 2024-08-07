package ru.fedin.treloclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;
import ru.fedin.treloclient.services.ColumnService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/column")
@Tag(name = "Колонка", description = "Работа с колонками")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @Operation(summary = "Создать колонку",
                description = "Добавляет колонку в конец")
    @PostMapping
    ResponseEntity create(@RequestBody DeskColumnReq column){
        if (columnService.create(column))
            return new ResponseEntity<>(CREATED);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Найти колонку по id")
    @GetMapping("/{columnId}")
    ResponseEntity getColumn(@PathVariable int columnId){
        var column = columnService.findById(columnId);
        if (column.getId() == 0)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(column, HttpStatus.OK);
    }

    @Operation(summary = "Удалить колонку",
    description = "Удаляет вместе с задачами")
    @DeleteMapping("/{columnId}")
    ResponseEntity removeColumn(@PathVariable int columnId){
        if (columnService.removeColumn(columnId))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "Переименовать колонку")
    @PutMapping("/{columnId}")
    ResponseEntity renameColumn(@PathVariable int columnId,
                            @RequestBody
                            @Parameter(description = "Новое имя") String newName){
        if (columnService.rename(columnId, newName))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

    @Operation(summary = "передвинуть колонку")
    @PutMapping("/{columnId}/move")
    ResponseEntity moveColumn(@PathVariable int columnId,
                          @Parameter(description = "Смещение колнки. Значения меньше 0 двигуют уолонку к предыдущим, большее - к следующим")
                          @RequestParam int offset){
        if (columnService.move(columnId, offset))
            return new ResponseEntity<>(OK);
        return new ResponseEntity<>(BAD_REQUEST);
    }

}
