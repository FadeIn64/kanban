package ru.fedin.trelo.configurations.kafka;

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
import ru.fedin.trelo.dtos.DeskDTO;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Configuration
@EnableKafka
public class KafkaServerConfiguration {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${kafka.topic.desk}")
    private String deskTopic;


    @Bean
    NewTopic desk(){
        return TopicBuilder.name(deskTopic)
                .partitions(10)
                .build();
    }

    private ConsumerFactory<UUID, DeskDTO> deskConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        return new DefaultKafkaConsumerFactory<>(props, new UUIDDeserializer(), jsonDeserializer());
    }

    private JsonDeserializer<DeskDTO> jsonDeserializer(){
        JsonDeserializer<DeskDTO> deserializer = new JsonDeserializer<>(DeskDTO.class);
        deserializer.addTrustedPackages("*");
        deserializer.ignoreTypeHeaders();
        return deserializer;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<UUID, DeskDTO> deskKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<UUID, DeskDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(deskConsumerFactory());
        return factory;
    }



}
