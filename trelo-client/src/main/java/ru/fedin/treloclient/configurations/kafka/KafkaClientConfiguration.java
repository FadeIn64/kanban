package ru.fedin.treloclient.configurations.kafka;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.UUIDSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import ru.fedin.treloclient.dtos.requests.DeskColumnReq;
import ru.fedin.treloclient.dtos.requests.DeskReq;
import ru.fedin.treloclient.dtos.requests.DeskTaskReq;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
public class KafkaClientConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Configuration
    public class DeskClientConfiguration{
        public ProducerFactory<UUID, DeskReq> deskProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("DeskTemplate")
        public KafkaTemplate<UUID, DeskReq> deskKafkaTemplate() {
            return new KafkaTemplate<>(deskProducerFactory());
        }
    }

    @Configuration
    public class ColumnClientConfiguration{
        public ProducerFactory<UUID, DeskColumnReq> columnProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("ColumnTemplate")
        public KafkaTemplate<UUID, DeskColumnReq> columnkKafkaTemplate() {
            return new KafkaTemplate<>(columnProducerFactory());
        }
    }

    @Configuration
    public class TaskClientConfiguration{
        public ProducerFactory<UUID, DeskTaskReq> taskProducerFactory() {
            Map<String, Object> configProps = new HashMap<>();
            configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
            configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, UUIDSerializer.class);
            configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
            return new DefaultKafkaProducerFactory<>(configProps);
        }

        @Bean("TaskTemplate")
        public KafkaTemplate<UUID, DeskTaskReq> taskKafkaTemplate() {
            return new KafkaTemplate<>(taskProducerFactory());
        }
    }



}
