package org.example;

import java.util.HashMap;
import java.util.Map;

public class CollectionCount {
    public <T> Map<T, Integer> arrayToMap(T[] innerArray) {
        Map<T, Integer> collectionCount = new HashMap<>();
        for (T element : innerArray) {
            collectionCount.put(element, collectionCount.getOrDefault(element, 0) + 1);
        }
        return collectionCount;
    }
}
