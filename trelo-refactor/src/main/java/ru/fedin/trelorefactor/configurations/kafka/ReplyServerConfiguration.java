package ru.fedin.trelorefactor.configurations.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.fedin.trelorefactor.messaging.ColumnAction;
import ru.fedin.trelorefactor.messaging.DeskAction;
import ru.fedin.trelorefactor.messaging.Message;
import ru.fedin.trelorefactor.messaging.TaskAction;
import ru.fedin.trelorefactor.models.ColumnModel;
import ru.fedin.trelorefactor.models.DeskModel;
import ru.fedin.trelorefactor.models.TaskModel;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ReplyServerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Configuration
    public class DeskClientConfiguration{

        @Value("${kafka.topic.desk}")
        private String topic;

        private String replyTopic(){
            return topic.concat("-reply");
        }

        @Bean
        public NewTopic replyDeskTopic(){
            return TopicBuilder.name(replyTopic())
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, Message<DeskModel, DeskAction>> deskProducerFactory() {
            return getUuidMessageDefaultKafkaProducerFactory(new TypeReference<Message<DeskModel, DeskAction>>() {});
        }

        @Bean("DeskTemplate")
        public KafkaTemplate<UUID, Message<DeskModel, DeskAction>> deskKafkaTemplate() {
            KafkaTemplate<UUID, Message<DeskModel, DeskAction>> template;
            template = new KafkaTemplate<>(deskProducerFactory());
            template.setDefaultTopic(replyTopic());
            return template;
        }
    }

    @Configuration
    public class ColumnClientConfiguration{

        @Value("${kafka.topic.column}")
        private String topic;

        private String replyTopic(){
            return topic.concat("-reply");
        }

        @Bean
        public NewTopic replyColumnTopic(){
            return TopicBuilder.name(replyTopic())
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, Message<ColumnModel, ColumnAction>> columnProducerFactory() {
           return getUuidMessageDefaultKafkaProducerFactory(new TypeReference<Message<ColumnModel, ColumnAction>>() {});
        }

        @Bean("ColumnTemplate")
        public KafkaTemplate<UUID, Message<ColumnModel, ColumnAction>> columnkKafkaTemplate() {
            KafkaTemplate<UUID, Message<ColumnModel, ColumnAction>> template = new KafkaTemplate<>(columnProducerFactory());
            template.setDefaultTopic(replyTopic());
            return template;
        }
    }

    @Configuration
    public class TaskClientConfiguration{

        @Value("${kafka.topic.task}")
        private String topic;

        private String replyTopic(){
            return topic.concat("-reply");
        }

        @Bean
        public NewTopic replyTaskTopic(){
            return TopicBuilder.name(replyTopic())
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, Message<TaskModel, TaskAction>> taskProducerFactory() {
            return getUuidMessageDefaultKafkaProducerFactory(new TypeReference<Message<TaskModel, TaskAction>>() {});
        }

        @Bean("TaskTemplate")
        public KafkaTemplate<UUID, Message<TaskModel, TaskAction>> taskKafkaTemplate() {
            KafkaTemplate<UUID, Message<TaskModel, TaskAction>> template = new KafkaTemplate<>(taskProducerFactory());
            template.setDefaultTopic(replyTopic());
            return template;
        }
    }

    private <E> DefaultKafkaProducerFactory<UUID, E> getUuidMessageDefaultKafkaProducerFactory(TypeReference<E> clazz) {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new DefaultKafkaProducerFactory<>(configProps);
    }

}
