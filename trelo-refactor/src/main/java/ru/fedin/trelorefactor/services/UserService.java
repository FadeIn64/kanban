package ru.fedin.trelorefactor.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.User;
import ru.fedin.trelorefactor.mappers.entities.UserMapper;
import ru.fedin.trelorefactor.repositories.jpa.UserRepository;
import ru.fedin.trelorefactor.requests.RegistrationReq;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    public UserDto createUser(RegistrationReq request){
        User user = User.builder()
                .id(0L)
                .name(request.getUsername())
                .email(request.getEmail())
                .build();
        return userMapper.toDto(userRepository.save(user));
    }
}
