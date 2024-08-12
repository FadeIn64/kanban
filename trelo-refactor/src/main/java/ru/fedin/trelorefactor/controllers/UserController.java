package ru.fedin.trelorefactor.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.requests.RegistrationReq;
import ru.fedin.trelorefactor.services.security.UserDetailsServiceImpl;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserDetailsServiceImpl userDetailsService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UserDto register(@RequestBody RegistrationReq request){
        return userDetailsService.registerUser(request);
    }

    @GetMapping("/{username}")
    UserDetails get(@PathVariable String username){
        return userDetailsService.loadUserByUsername(username);
    }

}
