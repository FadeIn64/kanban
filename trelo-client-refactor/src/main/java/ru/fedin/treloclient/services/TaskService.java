package ru.fedin.treloclient.services;

import com.fasterxml.uuid.Generators;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.stream.Task;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.TaskDto;
import ru.fedin.treloclient.mappers.models.TaskModelMapper;
import ru.fedin.treloclient.messaging.*;
import ru.fedin.treloclient.models.DeskModel;
import ru.fedin.treloclient.models.TaskModel;
import ru.fedin.treloclient.models.UserModel;
import ru.fedin.treloclient.repositories.redis.TaskRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final RestClient restClient;
    private final KafkaTemplate<UUID, Message<TaskModel, TaskAction>> taskTemplate;
    private final TaskModelMapper taskMapper;
    private final TaskRepository taskRepository;




    private TaskModel findModelById(long id) {
        return restClient
                .get()
                .uri(String.format("/task/%d", id))
                .retrieve()
                .body(TaskModel.class);
    }

    private void send(TaskModel model, TaskAction action) {
        Message<TaskModel, TaskAction> message = new Message<>();
        message.setAction(action);
        message.setMessage(model);
        message.setStatus(new MessageStatus(Status.OK, "no reason"));
        UUID uuid = Generators.timeBasedGenerator().generate();
        taskTemplate.sendDefault(uuid, message);
    }

    public TaskDto findById(long taskId) {
        return taskMapper.toDto(findModelById(taskId));
    }

    public void create(@Valid TaskDto task) {
        TaskModel model = taskMapper.toEntity(task);
        send(model, TaskAction.CREATE);
    }

    public void removeTask(Long taskId) {
        TaskModel model = findModelById(taskId);
        send(model, TaskAction.REMOVE);
    }

    public void change(@Valid TaskDto task) {
        TaskModel model = taskMapper.toEntity(task); //todo: возможны баги
        send(model, TaskAction.CHANGE);
    }

    public void changePerformer(long taskId, Long performer) {
        TaskModel model = findModelById(taskId);
        model.setPerformer(UserModel.builder().id(performer).build());
        send(model, TaskAction.ChANGE_PERFORMER);
    }

    public void changeColumn(Long taskId, Long columnId) {
        TaskModel model = findModelById(taskId);
        model.setColumnId(columnId);
        send(model, TaskAction.CHANGE_COLUMN);
    }

    public TaskModel save(TaskModel taskModel){
        return taskRepository.save(taskModel);
    }

    public void remove(TaskModel model) {
        taskRepository.deleteById(model.getId());
    }
}
