package ru.fedin.treloclient.configurations.kafka;

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
import ru.fedin.treloclient.messaging.ColumnAction;
import ru.fedin.treloclient.messaging.DeskAction;
import ru.fedin.treloclient.messaging.Message;
import ru.fedin.treloclient.messaging.TaskAction;
import ru.fedin.treloclient.models.ColumnModel;
import ru.fedin.treloclient.models.DeskModel;
import ru.fedin.treloclient.models.TaskModel;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class ReplyClientConfiguration {

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
        public String replyDeskTopic(){
            return deskTopic.concat("-reply");
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, Message<DeskModel, DeskAction>> deskKafkaListenerContainerFactory() {
            return ReplyClientConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<Message<DeskModel, DeskAction>>() {});
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
        public String replyColumnTopic(){
            return columnTopic.concat("-reply");
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, Message<ColumnModel, ColumnAction>> columnKafkaListenerContainerFactory() {
            return ReplyClientConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<Message<ColumnModel, ColumnAction>>() {});
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
        public String replyTaskTopic(){
            return taskTopic.concat("-reply");
        }

        @Bean
        public ConcurrentKafkaListenerContainerFactory<UUID, Message<TaskModel, TaskAction>> taskKafkaListenerContainerFactory() {
            return ReplyClientConfiguration.this.abstractKafkaListenerContainerFactory(new TypeReference<Message<TaskModel, TaskAction>>() {});
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
                this.jsonDeserializer(type));
    }

    private <T> ConcurrentKafkaListenerContainerFactory<UUID, T> abstractKafkaListenerContainerFactory(TypeReference<T> type) {
        ConcurrentKafkaListenerContainerFactory<UUID, T> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(this.abstractConsumerFactory(type));
        return factory;
    }
}
