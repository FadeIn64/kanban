package ru.fedin.treloclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.treloclient.dtos.DeskDTO;
import ru.fedin.treloclient.services.DeskService;

@RestController
@RequestMapping("/desk")
@Tag(name = "Доска", description = "Работа с доской")
@RequiredArgsConstructor
public class DeskController {

    private final DeskService deskService;

    @Operation(summary = "Найти доску по id")
    @GetMapping("/{deskId}")
    ResponseEntity getDesk(@PathVariable @Parameter(description = "Id Доски") int deskId){
        var desk = deskService.findById(deskId);
        if (desk.getId() == 0)
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(desk, HttpStatus.OK);
    }

    @Operation(summary = "Создать доску",
            description = "Возвращает новую доску, включая всю информацию о колонках, задачах, участников.")
    @PostMapping
    ResponseEntity createDesk(@RequestBody DeskDTO desk){

        try {
            desk = deskService.create(desk);
        }
        catch (Exception e){
            return new ResponseEntity<>(
                    e.getClass().getName() + "\n" + e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }

        return new ResponseEntity<>(
                desk,
                HttpStatus.CREATED
        );
    }

    @Operation(summary = "Удалить доску по Id",
    description = "Удаляет всю доску с ее элементами")
    @DeleteMapping("/{deskId}")
    ResponseEntity deleteDesk(@PathVariable int deskId){
        deskService.delete(deskId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(summary = "Переименовать доску")
    @PutMapping("/{deskId}")
    HttpStatus rename(@PathVariable int deskId,
                      @RequestBody
                      @Parameter(description = "Новое имя") String newName){
        var desk = deskService.rename(deskId, newName);
        if (desk.getId() == 0)
            return HttpStatus.NOT_FOUND;
        return HttpStatus.OK;
    }

    @Operation(summary = "Добавить нового участника",
    description = "Возвращает список участников")
    @PostMapping("/{deskId}/contributor")
    ResponseEntity addContributor(@PathVariable int deskId, @RequestBody String user){

        var contributors = deskService.addContributor(deskId, user);
        if (contributors.size() == 0)
            return new ResponseEntity("Desk no exist", HttpStatus.BAD_REQUEST);

        return new ResponseEntity(contributors, HttpStatus.OK);
    }

    @Operation(summary = "Удалить участника")
    @DeleteMapping("/{deskId}/contributor")
    ResponseEntity removeContributor(@PathVariable Integer deskId, @RequestBody String user){
        var res = deskService.removeContributor(deskId, user);
        return (res) ? new ResponseEntity(HttpStatus.ACCEPTED)
                : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }




}
