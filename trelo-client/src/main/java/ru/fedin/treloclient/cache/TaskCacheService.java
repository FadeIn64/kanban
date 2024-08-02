package ru.fedin.treloclient.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;
import ru.fedin.treloclient.dtos.response.DeskTaskRes;
import ru.fedin.treloclient.repositories.redis.DeskRepository;
import ru.fedin.treloclient.repositories.redis.TaskRepository;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskCacheService implements CacheService<DeskTaskRes, Integer>{

    private final TaskRepository taskRepository;
    private final DeskRepository deskRepository;
    @Override
    public DeskTaskRes save(DeskTaskRes obj) {

        obj = taskRepository.save(obj);
        int id = obj.getId();
        var opt = deskRepository.findById(obj.getDesk());
        if (opt.isEmpty())
            return obj;
        var desk = opt.get();
        var oldOpt = desk.getDeskTasks().stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (oldOpt.isEmpty())
            return obj;
        desk.getDeskTasks().remove(oldOpt.get());
        desk.getDeskTasks().add(obj);
        deskRepository.save(desk);
        return obj;
    }

    @Override
    public Optional<DeskTaskRes> findById(Integer id) {
        return taskRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        var opt = taskRepository.findById(id);
        if (opt.isEmpty())
            return;
        var obj = opt.get();
        taskRepository.delete(obj);
        var optDesk = deskRepository.findById(obj.getDesk());
        if (optDesk.isEmpty())
            return;
        var desk = optDesk.get();
        desk.setDeskTasks(
                desk.getDeskTasks().stream()
                        .filter(c -> !Objects.equals(c.getId(), obj.getId()))
                        .toList()
        );
        deskRepository.save(desk);
    }

}
