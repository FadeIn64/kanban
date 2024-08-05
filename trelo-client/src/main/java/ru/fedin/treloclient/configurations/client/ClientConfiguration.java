package ru.fedin.treloclient.configurations.client;

import com.fasterxml.jackson.core.StreamReadFeature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ClientConfiguration {

    @Value("${client.server.root-uri}")
    private String rootUri;

    @Bean
    public RestClient restClient(){
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(
                Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.MULTIPART_FORM_DATA)
        );
        return RestClient.builder()
                .baseUrl(rootUri)
                .messageConverters(httpMessageConverters -> {
            httpMessageConverters.add(converter);
        }).build();
    }
}
