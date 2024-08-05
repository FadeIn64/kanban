package ru.fedin.treloclient.services;

import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;
import ru.fedin.treloclient.dtos.response.TaskHistoryRes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class HistoryService {

    private final RestClient restClient;

    @Transactional
    public List<TaskHistoryRes> findAllByTaskAndChangeDate(Integer taskId,
                                                           LocalDateTime changeDate,
                                                           LocalDateTime changeDate2){
        Map<String, LocalDateTime> params = new HashMap<>();
        params.put("from", changeDate);
        params.put("to", changeDate2);

        try {
            List<TaskHistoryRes> history = restClient.get().uri("/task/"+taskId+"/history", params)
                    .retrieve()
                    .toEntity(new ParameterizedTypeReference<List<TaskHistoryRes>>() {})
                    .getBody();
            return  history;
        }catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
