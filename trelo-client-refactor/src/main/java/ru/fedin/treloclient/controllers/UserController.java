package ru.fedin.treloclient.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.UserDto;
import ru.fedin.treloclient.mappers.models.UserModelMapper;
import ru.fedin.treloclient.models.UserModel;
import ru.fedin.treloclient.requests.RegistrationReq;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final RestClient restClient;
    private final UserModelMapper userModelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto register(@RequestBody @Valid RegistrationReq request){
        return userModelMapper.toDto(restClient.post()
                .uri("/users")
                .header(HttpHeaders.AUTHORIZATION, "")
                .retrieve()
                .toEntity(UserModel.class)
                .getBody());
    }


}
