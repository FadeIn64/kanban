package ru.fedin.treloclient.services;

import com.fasterxml.uuid.Generators;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.ColumnDto;
import ru.fedin.treloclient.mappers.models.ColumnModelMapper;
import ru.fedin.treloclient.messaging.ColumnAction;
import ru.fedin.treloclient.messaging.Message;
import ru.fedin.treloclient.messaging.MessageStatus;
import ru.fedin.treloclient.messaging.Status;
import ru.fedin.treloclient.models.ColumnModel;
import ru.fedin.treloclient.repositories.redis.ColumnRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ColumnService {
    private final RestClient restClient;
    private final KafkaTemplate<UUID, Message<ColumnModel, ColumnAction>> columnTemplate;
    private final ColumnModelMapper columnMapper;
    private final ColumnRepository columnRepository;


    public void create(@Valid ColumnDto column, long deskId) {
        column.setDeskId(deskId);
        ColumnModel model = columnMapper.toEntity(column);
        send(model, ColumnAction.CREATE);
    }

    public ColumnDto findById(long columnId) {
        return columnMapper.toDto(findModelById(columnId));
    }

    private ColumnModel findModelById(long columnId) {
        return columnRepository.findById(columnId).orElse(save(
                    restClient.get()
                    .uri(String.format("/column/%d", columnId))
                    .retrieve()
                    .body(ColumnModel.class)
        ));
    }

    public void remove(long columnId) {
        ColumnModel model = findModelById(columnId);
        send(model, ColumnAction.REMOVE);
    }

    public void rename(long columnId, String newName) {
        ColumnModel model = findModelById(columnId);
        model.setName(newName);
        send(model, ColumnAction.RENAME);
    }

    public void move(long columnId, int order) {
        ColumnModel model = findModelById(columnId);
        model.setOrder(order);
        send(model, ColumnAction.MOVE);
    }

    private void send(ColumnModel column, ColumnAction action) {
        Message<ColumnModel, ColumnAction> message = new Message<>();
        message.setAction(action);
        message.setMessage(column);
        message.setStatus(new MessageStatus(Status.OK, "no reason"));
        UUID uuid = Generators.timeBasedGenerator().generate();
        columnTemplate.sendDefault(uuid, message);
    }
    public ColumnModel save(ColumnModel columnModel) {
        return columnRepository.save(columnModel);
    }

    public void removeFromCache(ColumnModel columnModel){
        columnRepository.deleteById(columnModel.getId());
    }
}
