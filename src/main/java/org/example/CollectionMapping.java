package org.example;

import java.lang.reflect.Array;
import java.util.function.Function;

public class CollectionMapping {

    public <T> T[] arrayMapping(T[] inputArray, Function<T, T> functionClass) {
        if (inputArray == null || functionClass == null) {
            throw new RuntimeException("Array and functional class must not be null");
        }

        T[] newArray = (T[]) Array.newInstance(inputArray.getClass().getComponentType(), inputArray.length);
        for (int i = 0; i < inputArray.length; i++) {
            newArray[i] = functionClass.apply(inputArray[i]);
        }
        return newArray;
    }
}