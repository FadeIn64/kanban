package ru.fedin.trelorefactor.messaging;

import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.services.ColumnService;
import ru.fedin.trelorefactor.services.DeskService;

public enum ColumnAction implements AbstractAction {

    CREATE(){
        @Override
        public ColumnDto action(ColumnDto columnDto, ColumnService columnService) {
            return columnService.create(columnDto, columnDto.getDeskId());
        }
    },
    REMOVE(){
        @Override
        public ColumnDto action(ColumnDto columnDto, ColumnService columnService) {
            columnService.remove(columnDto.getId());
            return columnDto;
        }
    },
    RENAME(){
        @Override
        public ColumnDto action(ColumnDto columnDto, ColumnService columnService) {
            return columnService.rename(columnDto.getId(), columnDto.getName());
        }
    },
    MOVE() {
        @Override
        public ColumnDto action(ColumnDto columnDto, ColumnService columnService) {
            return columnService.move(columnDto.getId(), columnDto.getOrder());
        }
    },
    CACHE(){
        @Override
        public ColumnDto action(ColumnDto columnDto, ColumnService columnService) {
            throw new UnsupportedOperationException("This consumer don't support caching");
        }
    };
    public abstract ColumnDto action(ColumnDto columnDto, ColumnService columnService);

}
