package ru.fedin.treloclient.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.fedin.treloclient.dtos.response.DeskRes;
import ru.fedin.treloclient.repositories.redis.DeskRepository;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeskCacheService implements CacheService<DeskRes, Integer>{

    private final DeskRepository deskRepository;

    @Override
    public DeskRes save(DeskRes obj) {
        return deskRepository.save(obj);
    }

    @Override
    public Optional<DeskRes> findById(Integer id) {
        return deskRepository.findById(id);
    }

    @Override
    public void delete(Integer id) {
        deskRepository.deleteById(id);
    }
}
