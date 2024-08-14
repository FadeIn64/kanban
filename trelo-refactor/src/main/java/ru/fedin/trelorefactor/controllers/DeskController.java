package ru.fedin.trelorefactor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.services.DeskService;

import static org.springframework.http.HttpStatus.ACCEPTED;


@RestController
@RequestMapping("/desk")
@Tag(name = "Доска", description = "Работа с доской")
@RequiredArgsConstructor
public class DeskController {

    private final DeskService deskService;

    @Operation(summary = "Найти доску по id")
    @GetMapping("/{deskId}")
    DeskDto getDesk(@PathVariable @Parameter(description = "Id Доски") long deskId){
        return deskService.findById(deskId);
    }

    @Operation(summary = "Создать доску",
            description = "Возвращает новую доску, включая всю информацию о колонках, задачах, участников.")
    @PostMapping
    DeskDto createDesk(@RequestBody DeskDto desk){
        desk.setId(0L);
        return deskService.create(desk);
    }

//    @Operation(summary = "Удалить доску по Id",
//    description = "Удаляет всю доску с ее элементами")
//    @DeleteMapping("/{deskId}")
//    ResponseEntity deleteDesk(@PathVariable int deskId){
//        deskService.delete(deskId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//
    @Operation(summary = "Переименовать доску")
    @PutMapping("/{deskId}")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    boolean rename(@PathVariable long deskId,
                      @RequestBody
                      @Parameter(description = "Новое имя") String newName){
        return deskService.rename(deskId, newName);
    }
//
//    @Operation(summary = "Добавить нового участника",
//    description = "Возвращает список участников")
//    @PostMapping("/{deskId}/contributor")
//    ResponseEntity addContributor(@PathVariable int deskId, @RequestBody String user){
//
//        var contributors = deskService.addContributor(deskId, user);
//        if (contributors.size() == 0)
//            return new ResponseEntity("Desk no exist", HttpStatus.BAD_REQUEST);
//
//        return new ResponseEntity(contributors, HttpStatus.OK);
//    }
//
//    @Operation(summary = "Удалить участника")
//    @DeleteMapping("/{deskId}/contributor")
//    ResponseEntity removeContributor(@PathVariable Integer deskId, @RequestBody String user){
//        var res = deskService.removeContributor(deskId, user);
//        return (res) ? new ResponseEntity(HttpStatus.ACCEPTED)
//                : new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }




}
