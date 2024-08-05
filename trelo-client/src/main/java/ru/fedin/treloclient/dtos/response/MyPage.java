package ru.fedin.treloclient.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MyPage<T> {
    private List<T> content;
    private long totalPages;
    private long totalElements;
    private long size;
    private long number;
    private long numberOfElements;
    private boolean first;
    private boolean last;
    private boolean empty;

}
