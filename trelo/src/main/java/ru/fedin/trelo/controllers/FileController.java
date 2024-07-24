package ru.fedin.trelo.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelo.dtos.minio.FileDTO;
import ru.fedin.trelo.services.FIleService;
import ru.fedin.trelo.services.minio.MinioService;

import java.io.IOException;

@RestController
@RequestMapping("/files")
public class FileController {
    @Autowired
    private MinioService minioService;

    @Autowired
    private FIleService fIleService;

    @GetMapping
    public ResponseEntity<Object> getFiles() {
        return ResponseEntity.ok(minioService.getListObjects());
    }

    @PostMapping(value = "/{taskId}/upload")
    public ResponseEntity<Object> upload(@ModelAttribute FileDTO request, @PathVariable Integer taskId) {
        return ResponseEntity.ok().body(fIleService.upload(request, taskId));
    }

    @GetMapping("/{taskId}/{fileName}")
    public ResponseEntity<Object> getFile(@PathVariable String fileName, @PathVariable Integer taskId) throws IOException{
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(fIleService.getObject(fileName, taskId)));
    }
}
