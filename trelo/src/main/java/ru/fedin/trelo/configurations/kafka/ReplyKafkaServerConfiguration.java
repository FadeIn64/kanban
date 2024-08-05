package ru.fedin.trelo.configurations.kafka;

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
import ru.fedin.trelo.dtos.kafka.DeskColumnRes;
import ru.fedin.trelo.dtos.kafka.DeskRes;
import ru.fedin.trelo.dtos.kafka.DeskTaskRes;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class ReplyKafkaServerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Configuration
    public class DeskClientConfiguration{

        @Value("${kafka.topic.desk}")
        private String deskTopic;

        @Bean
        public String replyDeskTopic(){
            return deskTopic.concat("-reply");
        }

        @Bean
        public NewTopic deskReply(String replyDeskTopic){
            return TopicBuilder.name(replyDeskTopic)
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, DeskRes> deskProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("ReplyDeskTemplate")
        public KafkaTemplate<UUID, DeskRes> replyDeskKafkaTemplate() {
            return new KafkaTemplate<>(deskProducerFactory());
        }

    }

    @Configuration
    public class ColumnClientConfiguration{

        @Value("${kafka.topic.column}")
        private String columnTopic;

        @Bean
        public String replyColumnTopic(){
            return columnTopic.concat("-reply");
        }

        @Bean
        public NewTopic columnReply(String replyColumnTopic){
            return TopicBuilder.name(replyColumnTopic)
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, DeskColumnRes> columnProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("ReplyColumnTemplate")
        public KafkaTemplate<UUID, DeskColumnRes> replyColumnKafkaTemplate() {
            return new KafkaTemplate<>(columnProducerFactory());
        }
    }

    @Configuration
    public class TaskClientConfiguration {

        @Value("${kafka.topic.task}")
        private String taskTopic;

        @Bean
        public String replyTaskTopic(){
            return taskTopic.concat("-reply");
        }

        @Bean
        public NewTopic taskReply(String replyTaskTopic){
            return TopicBuilder.name(replyTaskTopic)
                    .partitions(10)
                    .build();
        }

        public ProducerFactory<UUID, DeskTaskRes> taskProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("ReplyTaskTemplate")
        public KafkaTemplate<UUID, DeskTaskRes> replyTaskKafkaTemplate() {
            return new KafkaTemplate<>(taskProducerFactory());
        }
    }
}
