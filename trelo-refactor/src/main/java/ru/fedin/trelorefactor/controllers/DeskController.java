package ru.fedin.trelorefactor.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.services.DeskService;

import java.util.List;

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

    @Operation(summary = "Удалить доску по Id",
    description = "Удаляет всю доску с ее элементами")
    @DeleteMapping("/{deskId}")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    void deleteDesk(@PathVariable long deskId){
        deskService.delete(deskId);
    }

    @Operation(summary = "Переименовать доску")
    @PutMapping("/{deskId}")
    @ResponseBody
    @ResponseStatus(ACCEPTED)
    boolean rename(@PathVariable long deskId,
                      @RequestBody
                      @Parameter(description = "Новое имя") String newName){
        return deskService.rename(deskId, newName);
    }

    @Operation(summary = "Добавить нового участника",
    description = "Возвращает список участников")
    @PostMapping("/{deskId}/contributor")
    List<UserDto> addContributor(@PathVariable long deskId, @RequestBody long user){
        return deskService.addContributor(deskId, user);
    }
//
//    @Operation(summary = "Удалить участника")
//    @DeleteMapping("/{deskId}/contributor")
//    ResponseEntity removeContributor(@PathVariable Integer deskId, @RequestBody String user){
//        var res = deskService.removeContributor(deskId, user);
//        return (res) ? new ResponseEntity(HttpStatus.ACCEPTED)
//                : new ResponseEntity(HttpStatus.BAD_REQUEST);
//    }




}
