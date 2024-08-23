package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.FileDto;
import ru.fedin.treloclient.mappers.models.FileModelMapper;
import ru.fedin.treloclient.mappers.models.TaskModelMapper;
import ru.fedin.treloclient.models.TaskModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FileService {

    private final RestClient restClient;
    private final TaskService taskService;
    private final TaskModelMapper taskMapper;
    private final FileModelMapper fileMapper;


    public InputStream getObject(String fileName, Long taskId) throws IOException {
        byte[] response = restClient.get()
                .uri("/files/" + taskId + "/" + fileName)
                .retrieve()
                .body(new ParameterizedTypeReference<byte[]>() {
                });
        assert response != null;
        return new ByteArrayInputStream(response);
    }

    public FileDto upload(FileDto request, Long taskId) {

        MultiValueMap<String, Object> form = new LinkedMultiValueMap<>();
        form.add("description", request.getDescription());
        form.add("file", request.getFile().getResource());

        FileDto response = restClient.post()
                .uri("/files/"+taskId+"/upload")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(form)
                .retrieve()
                .toEntity(FileDto.class)
                .getBody();

        TaskModel task = taskMapper.toEntity(taskService.findById(taskId));
        task.getFiles().add(fileMapper.toEntity(response));
        taskService.save(task);
        return response;
    }

}
