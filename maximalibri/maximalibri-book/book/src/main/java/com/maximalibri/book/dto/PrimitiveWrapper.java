package com.maximalibri.book.dto;

public class PrimitiveWrapper<T> {
    private T value;

    public PrimitiveWrapper(T value) {
        this.value = value;
    }

    public PrimitiveWrapper() {
    }

    public T getValue() {
        return value;
    }
}
