package ru.fedin.deskswagger.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.deskswagger.schema.DeskColumn;

@RestController
@RequestMapping("/column")
@Tag(name = "Колонка", description = "Работа с колонками")
public class ColumnController {

    @Operation(summary = "Создать колонку")
    @PostMapping
    ResponseEntity create(@RequestBody Column column){
        return new ResponseEntity<>(new DeskColumn(1, column.deskId, column.name, column.nextId, 0),
                HttpStatus.CREATED);
    }

    @Operation(summary = "Найти колонку по id")
    @GetMapping("/{columnId}")
    ResponseEntity getColumn(@PathVariable int columnId){
        return new ResponseEntity<>(DeskColumn.DEFAULT.get(0), HttpStatus.OK);
    }

    @Operation(summary = "Удалить колонку",
    description = "Удаляет вместе с задачами")
    @DeleteMapping("/{columnId}")
    HttpStatus removeColumn(@PathVariable int columnId){
        return HttpStatus.OK;
    }

    @Operation(summary = "Переименовать колонку")
    @PutMapping("/{columnId}")
    HttpStatus renameColumn(@PathVariable String columnId,
                            @RequestBody
                            @Parameter(description = "Новое имя") String newName){
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "передвинуть колонку")
    @PutMapping("/{columnId}/move")
    HttpStatus moveColumn(@PathVariable String columnId,
                          @Parameter(description = "Смещение колнки. Значения меньше 0 двигуют уолонку к предыдущим, большее - к следующим")
                          @RequestParam int offset){
        return HttpStatus.ACCEPTED;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    static class Column{
        @Schema(description = "Имя колонки")
        String name;
        @Schema(description = "Индефикатор доски")
        int deskId;
        @Schema(description = "Индефикатор следующей колонки. \nЕсли указать 0 то колонка встанет в конец")
        int nextId;
    }
}
