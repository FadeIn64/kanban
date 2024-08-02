package ru.fedin.treloclient.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskColumnRes;
import ru.fedin.treloclient.repositories.redis.ColumnRepository;
import ru.fedin.treloclient.repositories.redis.DeskRepository;

import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ColumnCacheService implements CacheService<DeskColumnRes, Integer>{

    private final ColumnRepository columnRepository;
    private final DeskRepository deskRepository;
    @Override
    public DeskColumnRes save(DeskColumnRes obj) {

        obj = columnRepository.save(obj);
        int id = obj.getId();
        var opt = deskRepository.findById(obj.getDesk());
        if (opt.isEmpty())
            return obj;
        var desk = opt.get();
        var oldOpt = desk.getDeskColumns().stream()
                .filter(c -> c.getId() == id)
                .findFirst();
        if (oldOpt.isEmpty())
            return obj;
        desk.getDeskColumns().remove(oldOpt.get());
        desk.getDeskColumns().add(obj);
        deskRepository.save(desk);
        return obj;
    }

    @Override
    public Optional<DeskColumnRes> findById(Integer id) {
        return columnRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        var opt = columnRepository.findById(id);
        if (opt.isEmpty())
            return;
        var obj = opt.get();
        columnRepository.delete(obj);
        var optDesk = deskRepository.findById(obj.getDesk());
        if (optDesk.isEmpty())
            return;
        var desk = optDesk.get();
        desk.setDeskColumns(
                desk.getDeskColumns().stream()
                        .filter(c -> !Objects.equals(c.getId(), obj.getId()))
                        .toList()
        );
        deskRepository.save(desk);
    }
}
