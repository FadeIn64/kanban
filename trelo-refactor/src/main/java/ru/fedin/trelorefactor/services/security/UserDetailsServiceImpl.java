package ru.fedin.trelorefactor.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.exceptions.EntityNotFoundException;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.entities.UserMapper;
import ru.fedin.trelorefactor.repositories.jpa.UsersCredentialsDataRepository;
import ru.fedin.trelorefactor.requests.RegistrationReq;
import ru.fedin.trelorefactor.services.UserService;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersCredentialsDataRepository repository;
    private final UserMapper userMapper;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public UserDto registerUser(RegistrationReq request){
        UserDto user = userService.createUser(request);
        try {
            repository.insert(user.getId(), user.getName(), passwordEncoder.encode(request.getPassword())).orElseThrow(ModifyDataException::new);
        }catch (Exception e){
            throw new ModifyDataException("Insert error", e.getCause());
        }

        return user;
    }
}
