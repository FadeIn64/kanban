package ru.fedin.trelo.listeners;

import com.fasterxml.uuid.Generators;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.fedin.trelo.dtos.DeskContributorDTO;
import ru.fedin.trelo.dtos.DeskDTO;
import ru.fedin.trelo.dtos.kafka.DeskContributorRes;
import ru.fedin.trelo.dtos.kafka.DeskRes;
import ru.fedin.trelo.mappers.kafka.DeskMapperKafka;
import ru.fedin.trelo.services.DeskService;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class DeskListener {

    private final DeskService deskService;
    private final DeskMapperKafka deskMapper;
    private final KafkaTemplate<UUID, DeskRes> replyDeskTemplate;
    private final String replyDeskTopic;

    @KafkaListener(topics = "${kafka.topic.desk}",
                    groupId = "server",
                    containerFactory = "deskKafkaListenerContainerFactory")
    void listener( DeskRes desk){
        if (desk.getId() == null){
            desk = deskMapper.toEntity(deskService.create(deskMapper.toDto(desk)));
            send(desk);
            return;
        }

        if (desk.getName().equals("")){
            deskService.delete(desk.getId());
            send(desk);
            return;
        }

        var dto = deskService.findById(desk.getId());

        if (dto.getId() == 0)
            return;

        if (!dto.getName().equals(desk.getName())){
            desk = deskMapper.toEntity(deskService.rename(desk.getId(), desk.getName()));
            send(desk);
            return;
        }

        var newContrs = desk.getDeskContributors().stream()
                .map(DeskContributorRes::getContributor)
                .toList();
        var oldContrs = dto.getDeskContributors().stream()
                .map(DeskContributorDTO::getContributor)
                .toList();

        //Добавляем новых участников
        newContrs.stream()
                .filter(contr -> !oldContrs.contains(contr))
                .forEach(contr -> deskService.addContributor(dto.getId(), contr));

        //Удаляем старых участников
        oldContrs.stream()
                .filter(contr -> !newContrs.contains(contr))
                .forEach(contr -> deskService.removeContributor(dto.getId(), contr));
        send(desk);
    }

    private void send(DeskRes desk){
        replyDeskTemplate.send(replyDeskTopic, Generators.timeBasedGenerator().generate(), desk);
    }

}
