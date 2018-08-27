package com.smilej.bestmile.common.util;

import lombok.experimental.UtilityClass;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@UtilityClass
public class CollectionUtil {

    public static <T> Stream<T> nullableStream(List<T> list){
        return Optional.ofNullable(list).orElse(Collections.emptyList()).stream();
    }

    public static <T> Optional<T> firstElement(List<T> list){
        return Optional.ofNullable(list).filter(l -> !l.isEmpty()).map(l -> l.get(0));
    }

}