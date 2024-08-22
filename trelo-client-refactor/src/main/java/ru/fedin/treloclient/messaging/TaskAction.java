package ru.fedin.treloclient.messaging;


import ru.fedin.treloclient.models.TaskModel;
import ru.fedin.treloclient.services.TaskService;

public enum TaskAction implements AbstractAction {
    CREATE,
    CHANGE,
    ChANGE_PERFORMER,
    CHANGE_COLUMN,
    REMOVE(){
        @Override
        public TaskModel action(TaskModel model, TaskService taskService) {
            taskService.removeFromCache(model);
            return null;
        }
    },
    CACHE(){
        @Override
        public TaskModel action(TaskModel model, TaskService taskService) {
            return taskService.save(model);
        }
    };

    public TaskModel action(TaskModel model, TaskService taskService) {
        throw new UnsupportedOperationException(String.format("%s operation not supported", this.name()));
    }
}
