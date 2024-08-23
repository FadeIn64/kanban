package ru.fedin.trelorefactor.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fedin.trelorefactor.dtos.minio.FileDto;
import ru.fedin.trelorefactor.services.FileService;

import java.io.IOException;

@Controller
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService fIleService;


    @PostMapping(value = "/{taskId}/upload")
    public ResponseEntity<Object> upload(@ModelAttribute FileDto request, @PathVariable Long taskId) {
        return ResponseEntity.ok().body(fIleService.upload(request, taskId));
    }

    @GetMapping("/{taskId}/{fileName}")
    public ResponseEntity<Object> getFile(@PathVariable String fileName, @PathVariable Long taskId) throws IOException{
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(IOUtils.toByteArray(fIleService.getObject(fileName, taskId)));
    }
}
