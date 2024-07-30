package ru.fedin.treloclient.configurations.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientConfiguration {

    @Value("${client.server.root-uri}")
    private String rootUri;

    @Bean
    public RestClient restClient(){
        return RestClient.builder().baseUrl(rootUri).build();
    }
}
