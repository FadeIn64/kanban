package ru.fedin.trelorefactor.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.eintites.Desk;
import ru.fedin.trelorefactor.eintites.User;
import ru.fedin.trelorefactor.exceptions.EntityNotFound;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.mappers.DeskMapper;
import ru.fedin.trelorefactor.mappers.UserMapper;
import ru.fedin.trelorefactor.repositories.jpa.DeskRepository;
import ru.fedin.trelorefactor.repositories.jpa.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeskService {
    private final DeskRepository deskRepository;
    private final DeskMapper deskMapper;

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final DefaultColumnCreator defaultColumnCreator;


    public DeskDto findById(long id){
        return deskMapper.toDto(deskRepository.findById(id).orElseThrow(() -> new EntityNotFound("desk not found")));
    }

    @Transactional
    public DeskDto create(DeskDto dto){
        Desk entity = deskMapper.toEntity(dto);
        try {
            entity.getUsers().add(User.builder().id(dto.getAuthor().getId()).build());
            entity = deskRepository.save(entity);
        }
        catch (Exception e){
            log.error("Exception: ", e);
            throw new ModifyDataException("desk don't create");
        }
        entity.setColumns(defaultColumnCreator.createDefault(entity));

        return deskMapper.toDto(entity);
    }

    @Transactional
    public boolean rename(long deskId, String newName) {
        int res = deskRepository.updateNameById(deskId, newName);
        if (res < 0){
            throw new ModifyDataException("desk don't exist");
        }
        return true;
    }

    public void delete(long deskId) {
        deskRepository.deleteById(deskId);
    }

    @Transactional
    public List<UserDto> addContributor(long deskId, long userId) {
        Desk desk = deskRepository.findById(deskId).orElseThrow(() -> new ModifyDataException("desk don't exist"));
        User user = userRepository.findById(userId).orElseThrow(() -> new ModifyDataException("user don't exist"));
        List<UserDto> users = userMapper.toDto(desk.getUsers());
        if (!desk.getUsers().contains(user)){
            deskRepository.addUser(deskId, userId);
            users.add(userMapper.toDto(user));
        }
        return users;
    }

    @Transactional
    public List<UserDto> removeContributor(long deskId, long userId) {
        Desk desk = deskRepository.findById(deskId).orElseThrow(() -> new ModifyDataException("desk don't exist"));
        if (userId == desk.getAuthor().getId()) throw new ModifyDataException("can't delete author");
        User user = userRepository.findById(userId).orElseThrow(() -> new ModifyDataException("user don't exist"));
        List<UserDto> users = userMapper.toDto(desk.getUsers());
        if (desk.getUsers().contains(user)){
            deskRepository.deleteUser(deskId, userId);
            users.remove(userMapper.toDto(user));
        }
        return users;
    }
}
