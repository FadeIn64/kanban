package ru.fedin.trelo.services;

import io.minio.GetObjectArgs;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.fedin.trelo.dtos.minio.FileDTO;
import ru.fedin.trelo.logic.filenaming.FileName;
import ru.fedin.trelo.mappers.FileMapper;
import ru.fedin.trelo.repositories.jpa.DeskTaskRepository;
import ru.fedin.trelo.repositories.jpa.TaskFileRepository;
import ru.fedin.trelo.services.minio.MinioService;

import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class FIleService {
    private final MinioService minioService;
    private final TaskFileRepository fileRepository;
    private final DeskTaskRepository taskRepository;
    private final FileMapper fileMapper;
    private final FileName naming;

    @Transactional
    public FileDTO upload(FileDTO request, Integer taskID){

        if (!taskRepository.existsById(taskID))
            return null;

        request.setFilename(naming.getName(request.getFile().getOriginalFilename(),taskID.toString()));
        request.setUrl(naming.getUrl(request.getFile().getOriginalFilename(),taskID.toString()));
        request.setSize(request.getFile().getSize());

        var entity = fileMapper.toEntity(request);
        entity = fileRepository.save(entity);

        return minioService.uploadFile(request);
    }

    public InputStream getObject(String filename, Integer taskId) {
        if (!filename.startsWith(taskId.toString() + "_"))
            return null;
        return minioService.getObject(filename);
    }

}
