package ru.fedin.treloclient.controllers;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.requests.FileReq;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    @Autowired
    RestClient restClient;

    @GetMapping("/{taskId}/{fileName}")
    public ResponseEntity<Object> getFile(@PathVariable String fileName, @PathVariable Integer taskId) throws IOException {
        byte[] response = restClient.get()
                .uri("/files/"+taskId+"/"+fileName)
                .retrieve()
                .body(new ParameterizedTypeReference<byte[]>() {
                });
        if (response == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        InputStream stream = new ByteArrayInputStream(response);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(stream));
    }

    @SneakyThrows
    @PostMapping(value = "/{taskId}/upload")
    public ResponseEntity<String> upload(@ModelAttribute FileReq request, @PathVariable Integer taskId) {

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("description", request.getDescription());
        form.add("file", request.getFile().getResource());

        return restClient.post()
                .uri("/files/"+taskId+"/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(form)
                .retrieve()
                .toEntity(String.class);
    }

}
