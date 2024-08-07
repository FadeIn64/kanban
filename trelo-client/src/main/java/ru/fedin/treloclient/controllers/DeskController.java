package ru.fedin.treloclient.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.treloclient.dtos.requests.DeskReq;
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
    ResponseEntity createDesk(@RequestBody DeskReq desk){

        if (deskService.create(desk))
            return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @Operation(summary = "Удалить доску по Id",
    description = "Удаляет всю доску с ее элементами")
    @DeleteMapping("/{deskId}")
    ResponseEntity deleteDesk(@PathVariable int deskId){
        if (deskService.delete(deskId))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @Operation(summary = "Переименовать доску")
    @PutMapping("/{deskId}")
    ResponseEntity rename(@PathVariable int deskId,
                      @RequestBody
                      @Parameter(description = "Новое имя") String newName){

        if (deskService.rename(deskId, newName))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Operation(summary = "Добавить нового участника",
    description = "Возвращает список участников")
    @PostMapping("/{deskId}/contributor")
    ResponseEntity addContributor(@PathVariable int deskId, @RequestBody String user){

        if (deskService.addContributor(deskId, user))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("Desk no exist", HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Удалить участника")
    @DeleteMapping("/{deskId}/contributor")
    ResponseEntity removeContributor(@PathVariable Integer deskId, @RequestBody String user){
        if (deskService.removeContributor(deskId, user))
            return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>("Desk no exist", HttpStatus.BAD_REQUEST);
    }




}
