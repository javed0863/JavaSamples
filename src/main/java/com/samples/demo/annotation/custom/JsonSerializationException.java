package com.samples.demo.annotation.custom;

public class JsonSerializationException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public JsonSerializationException(String message) {
        super(message);
    }
}
