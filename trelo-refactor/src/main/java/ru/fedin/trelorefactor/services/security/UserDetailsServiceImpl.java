package ru.fedin.trelorefactor.services.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.User;
import ru.fedin.trelorefactor.eintites.UsersCredentialsData;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.mappers.UserMapper;
import ru.fedin.trelorefactor.repositories.jpa.UserRepository;
import ru.fedin.trelorefactor.repositories.jpa.UsersCredentialsDataRepository;
import ru.fedin.trelorefactor.requests.RegistrationReq;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersCredentialsDataRepository repository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username).orElseThrow(EntityNotFound::new);
    }

    @Transactional
    public UserDto registerUser(RegistrationReq request){
        User user = User.builder()
                .id(0L)
                .name(request.getUsername())
                .email(request.getEmail())
                .build();
        user = userRepository.save(user);
        UsersCredentialsData ucd = UsersCredentialsData.builder()
                .users(user)
                .id(user.getId())
                .username(user.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        ucd = repository.save(ucd);
        return userMapper.toDto(user);
    }
}
