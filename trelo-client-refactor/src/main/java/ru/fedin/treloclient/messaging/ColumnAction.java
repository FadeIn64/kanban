package ru.fedin.treloclient.messaging;


import ru.fedin.treloclient.models.ColumnModel;
import ru.fedin.treloclient.services.ColumnService;

public enum ColumnAction implements AbstractAction {

    CREATE,
    REMOVE(){
        @Override
        public ColumnModel action(ColumnModel column, ColumnService columnService) {
            columnService.removeFromCache(column);
            return null;
        }
    },
    RENAME,
    MOVE,
    CACHE(){
        @Override
        public ColumnModel action(ColumnModel column, ColumnService columnService) {
            return columnService.save(column);
        }
    };

    public ColumnModel action(ColumnModel column, ColumnService columnService) {
        throw new UnsupportedOperationException(String.format("%s operation not supported", this.name()));
    }


}
