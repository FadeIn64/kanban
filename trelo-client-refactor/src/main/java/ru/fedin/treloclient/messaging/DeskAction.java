package ru.fedin.treloclient.messaging;

import lombok.extern.slf4j.Slf4j;
import ru.fedin.treloclient.models.DeskModel;
import ru.fedin.treloclient.services.DeskService;

@Slf4j
public enum DeskAction implements AbstractAction {

    CREATE,
    REMOVE(){
        @Override
        public DeskModel action(DeskModel model, DeskService deskService) {
            deskService.removeFromeCache(model);
            return null;
        }
    },
    RENAME,
    ADD_CONTRIBUTOR,
    REMOVE_CONTRIBUTOR,
    CACHE(){
        @Override
        public DeskModel action(DeskModel model, DeskService deskService) {
            return deskService.save(model);
        }
    };

    public DeskModel action(DeskModel model, DeskService deskService) {
        throw new UnsupportedOperationException(String.format("%s operation not supported", this.name()));
    }
}
