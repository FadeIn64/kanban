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
import ru.fedin.deskswagger.schema.Desk;
import ru.fedin.deskswagger.schema.DeskColumn;
import ru.fedin.deskswagger.schema.DeskTask;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/desk")
@Tag(name = "Доска", description = "Работа с доской")
public class DeskController {

    @Operation(summary = "Найти доску по id")
    @GetMapping("/{deskId}")
    ResponseEntity getDesk(@PathVariable @Parameter(description = "Id Доски") int deskId){
        if (deskId != 1)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(Desk.SAMPLE, HttpStatus.OK);
    }

    @Operation(summary = "Создать доску",
            description = "Возвращает новую доску, включая всю информацию о колонках, задачах, участников.")
    @PostMapping
    ResponseEntity createDesk(@RequestBody DeskReq desk){
        return new ResponseEntity<>(
                new Desk(2, desk.name, desk.author, DeskColumn.DEFAULT, List.of(desk.author), new ArrayList<DeskTask>()),
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Удалить доску по Id",
    description = "Удаляет всю доску с ее элементами")
    @DeleteMapping("/{deskId}")
    ResponseEntity deleteDesk(@PathVariable int deskId){
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Переименовать доску")
    @PutMapping("/{deskId}")
    HttpStatus rename(@PathVariable int deskId,
                      @RequestBody
                      @Parameter(description = "Новое имя") String newName){
        return HttpStatus.ACCEPTED;
    }

    @Operation(summary = "Добавить нового участника",
    description = "Возвращает список участников")
    @PostMapping("/{deskId}/contributor")
    ResponseEntity addContributor(@PathVariable int deskId, @RequestBody String user){
//        Desk.SAMPLE.getContributors().add(user);
        return new ResponseEntity(Desk.SAMPLE.getContributors(), HttpStatus.OK);
    }

    @Operation(summary = "Удалить участника")
    @DeleteMapping("/{deskId}/contributor")
    HttpStatus removeContributor(@PathVariable String deskId, @RequestBody String user){
        return HttpStatus.OK;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Schema(name = "Desk")
    static class DeskReq {
        @Schema(description = "Название доски")
        String name;
        @Schema(description = "Автор доски")
        String author;
    }

}
