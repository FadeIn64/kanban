package ru.fedin.treloclient.services;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.DeskDto;
import ru.fedin.treloclient.mappers.models.DeskModelMapper;
import ru.fedin.treloclient.messaging.DeskAction;
import ru.fedin.treloclient.messaging.Message;
import ru.fedin.treloclient.messaging.MessageStatus;
import ru.fedin.treloclient.messaging.Status;
import ru.fedin.treloclient.models.ColumnModel;
import ru.fedin.treloclient.models.DeskModel;
import ru.fedin.treloclient.models.TaskModel;
import ru.fedin.treloclient.models.UserModel;
import ru.fedin.treloclient.repositories.redis.ColumnRepository;
import ru.fedin.treloclient.repositories.redis.DeskRepository;
import ru.fedin.treloclient.repositories.redis.TaskRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DeskService {

    private final RestClient restClient;
    private final KafkaTemplate<UUID, Message<DeskModel, DeskAction>> deskTemplate;
    private final DeskModelMapper deskMapper;
    private final TaskRepository taskRepository;
    private final ColumnRepository columnRepository;
    private final DeskRepository deskRepository;

    public DeskDto findById(long deskId) {
        return deskMapper.toDto(findModelById(deskId));
    }

    private DeskModel findModelById(long deskId) {
        return deskRepository.findById(deskId)
                .orElse(save(
                    Objects
                    .requireNonNull(restClient.get()
                    .uri(String.format("/desk/%d", deskId))
                    .retrieve()
                    .body(DeskModel.class))
        ));
    }

    public void create(DeskDto desk) {
        DeskModel model = deskMapper.toEntity(desk);
        send(model, DeskAction.CREATE);
    }

    public void delete(long deskId) {
        DeskModel model = findModelById(deskId);
        send(model, DeskAction.REMOVE);
    }

    public void rename(long deskId, String newName) {
        DeskModel model = findModelById(deskId);
        model.setName(newName);
        send(model, DeskAction.RENAME);
    }

    public void addContributor(long deskId, long user) {
        DeskModel model = findModelById(deskId);
        model.setUsers(List.of(UserModel.builder().id(user).build()));
        send(model, DeskAction.ADD_CONTRIBUTOR);
    }

    public void removeContributor(long deskId, long user) {
        DeskModel model = findModelById(deskId);
        model.setUsers(List.of(UserModel.builder().id(user).build()));
        send(model, DeskAction.REMOVE_CONTRIBUTOR);
    }

    private void send(DeskModel desk, DeskAction action) {
        Message<DeskModel, DeskAction> message = new Message<>();
        message.setAction(action);
        message.setMessage(desk);
        message.setStatus(new MessageStatus(Status.OK, "no reason"));
        UUID uuid = Generators.timeBasedGenerator().generate();
        deskTemplate.sendDefault(uuid, message);
    }

    public DeskModel save(DeskModel deskModel){
        taskRepository.saveAll(deskModel.getTasks());
        columnRepository.saveAll(deskModel.getColumns());
        return deskRepository.save(deskModel);
    }

    public void removeFromeCache(DeskModel model) {
        taskRepository.deleteAllById(model.getTasks().stream().map(TaskModel::getId).toList());
        columnRepository.deleteAllById(model.getColumns().stream().map(ColumnModel::getId).toList());
        deskRepository.deleteById(model.getId());
    }
}
