package ru.fedin.trelorefactor.configurations.kafka;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.UUIDDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import ru.fedin.trelorefactor.dtos.ColumnDto;
import ru.fedin.trelorefactor.dtos.DeskDto;
import ru.fedin.trelorefactor.dtos.TaskDto;
import ru.fedin.trelorefactor.messaging.DeskAction;
import ru.fedin.trelorefactor.messaging.Message;
import ru.fedin.trelorefactor.models.DeskModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaServerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Configuration
    public class DeskConfiguration{
        @Value("${kafka.topic.desk}")
        private String deskTopic;


        @Bean
        public NewTopic desk(){
            return TopicBuilder.name(deskTopic)
                    .partitions(10)
                    .build();
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, Message<DeskModel, DeskAction>> deskKafkaListenerContainerFactory() {
            return KafkaServerConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<Message<DeskModel, DeskAction>>() {});
        }
    }

    @Configuration
    public class ColumnConfiguration{
        @Value("${kafka.topic.column}")
        private String columnTopic;


        @Bean
        public NewTopic column(){
            return TopicBuilder.name(columnTopic)
                    .partitions(10)
                    .build();
        }


        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, ColumnDto> columnKafkaListenerContainerFactory() {
            return KafkaServerConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<ColumnDto>() {});
        }
    }


    @Configuration
    public class TaskConfiguration{
        @Value("${kafka.topic.task}")
        private String taskTopic;


        @Bean
        public NewTopic task(){
            return TopicBuilder.name(taskTopic)
                    .partitions(10)
                    .build();
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, TaskDto> taskKafkaListenerContainerFactory() {
            return KafkaServerConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<TaskDto>() {});
        }
    }

    private <T> JsonDeserializer<T> jsonDeserializer(TypeReference<T> type){
        JsonDeserializer<T> deserializer = new JsonDeserializer<>(type);
        deserializer.addTrustedPackages("*");
        deserializer.ignoreTypeHeaders();
        return deserializer;
    }

    private <T> ConsumerFactory<UUID, T> abstractConsumerFactory(TypeReference<T> type) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new UUIDDeserializer(),
                KafkaServerConfiguration.this.jsonDeserializer(type));
    }

    private <T> ConcurrentKafkaListenerContainerFactory<UUID, T> abstractKafkaListenerContainerFactory(TypeReference<T> type) {
        ConcurrentKafkaListenerContainerFactory<UUID, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.abstractConsumerFactory(type));
        return factory;
    }
}
