package ru.fedin.trelorefactor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.services.ColumnService;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/column")
@Tag(name = "Колонка", description = "Работа с колонками")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @Operation(summary = "Создать колонку")
    @PostMapping("/{deskId}")
    @ResponseStatus(CREATED)
    @ResponseBody
    public ColumnDto create(@PathVariable long deskId,@RequestBody @Valid ColumnDto column){
        return columnService.create(column, deskId);
    }

    @Operation(summary = "Найти колонку по id")
    @GetMapping("/{columnId}")
    @ResponseBody
    public ColumnDto getColumn(@PathVariable long columnId){
        return columnService.findById(columnId);
    }

    @Operation(summary = "Удалить колонку",
    description = "Удаляет вместе с задачами")
    @DeleteMapping("/{columnId}")
    @ResponseStatus(ACCEPTED)
    void removeColumn(@PathVariable long columnId){
        columnService.remove(columnId);
    }

    @Operation(summary = "Переименовать колонку")
    @PutMapping("/{columnId}")
    ColumnDto renameColumn(@PathVariable long columnId,
                            @RequestBody
                            @Parameter(description = "Новое имя") String newName){
        return columnService.rename(columnId, newName);
    }

    @Operation(summary = "передвинуть колонку")
    @PutMapping("/{columnId}/move")
    ColumnDto moveColumn(@PathVariable long columnId,
                          @RequestParam int order){
        return columnService.move(columnId, order);
    }

}
