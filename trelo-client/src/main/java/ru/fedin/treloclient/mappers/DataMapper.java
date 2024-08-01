package ru.fedin.treloclient.mappers;

import java.util.Collection;
import java.util.List;

public interface DataMapper<S, Q> {

    S toRes(Q dto);
    Q toReq(S entity);

    Collection<S> toRes(Collection<Q> dtos);
    Collection<Q> toReq(Collection<S> entities);
    List<S> toRes(List<Q> dtos);
    List<Q> toReq(List<S> entities);
}
