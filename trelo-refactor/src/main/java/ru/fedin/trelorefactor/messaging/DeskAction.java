package ru.fedin.trelorefactor.messaging;

import lombok.extern.slf4j.Slf4j;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.dtos.UserDto;
import ru.fedin.trelorefactor.exceptions.ModifyDataException;
import ru.fedin.trelorefactor.services.DeskService;

import java.util.List;

@Slf4j
public enum DeskAction implements AbstractAction {

    LOG() {
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            log.info("Received Desk Message: {}", deskDto);
            return deskDto;
        }
    },
    CREATE(){
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            deskDto.setId(0L);
            return deskService.create(deskDto);
        }
    },
    RENAME(){
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            deskService.rename(deskDto.getId(), deskDto.getName());
            return deskDto;
        }
    },
    ADD_CONTRIBUTOR(){
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            return this.contrOperation(deskDto, deskService::addContributor);
        }
    },

    REMOVE_CONTRIBUTOR(){
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            return this.contrOperation(deskDto, deskService::removeContributor);
        }
    },

    CACHE(){
        @Override
        public DeskDto action(DeskDto deskDto, DeskService deskService) {
            throw new UnsupportedOperationException("This consumer don't support caching");
        }
    }
    ;

    public abstract DeskDto action(DeskDto deskDto, DeskService deskService);

    protected DeskDto contrOperation(DeskDto deskDto , ContributorOperation operation) {
        if (deskDto.getUsers().size() != 1){
            throw new ModifyDataException(String.format("Incorrect contributors size for action: %s, list size %d", this.name(), deskDto.getUsers().size()));
        }
        List<UserDto> users = operation.action(deskDto.getId(), deskDto.getUsers().get(0).getId());
        deskDto.setUsers(users);
        return deskDto;
    }

    @FunctionalInterface
    protected interface ContributorOperation {
        List<UserDto> action(long deskId, long userID);
    }
}
