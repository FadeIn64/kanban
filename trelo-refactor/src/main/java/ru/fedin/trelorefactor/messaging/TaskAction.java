package ru.fedin.trelorefactor.messaging;

import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.services.TaskService;

public enum TaskAction implements AbstractAction {
    CREATE() {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            return taskService.create(taskDto);
        }
    },
    CHANGE() {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            return taskService.change(taskDto);
        }
    },
    ChANGE_PERFORMER() {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            Long performerId = null;
            if (taskDto.getPerformer() != null) {
                performerId = taskDto.getPerformer().getId();
            }
            taskDto.setPerformer(taskService.changePerformer(taskDto.getId(), performerId));
            return taskDto;
        }
    },
    CHANGE_COLUMN {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            taskService.changeColumn(taskDto.getId(), taskDto.getColumnId());
            return taskDto;
        }
    },
    REMOVE {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            taskService.removeTask(taskDto.getId());
            return taskDto;
        }
    },
    CACHE {
        @Override
        public TaskDto action(TaskDto taskDto, TaskService taskService) {
            throw new UnsupportedOperationException("This consumer don't support caching");
        }
    };

    public abstract TaskDto action(TaskDto taskDto, TaskService taskService);
}
