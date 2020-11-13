package com.samples.demo.annotation.custom;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class JsonSerializer {
    public String serialize(Object object) throws JsonSerializationException {
        try {

            checkIfSerializable(object);
            initializeObject(object);
            return getJsonString(object);

        } catch (Exception e) {
            throw new JsonSerializationException(e.getMessage());
        }
    }

    private void checkIfSerializable(Object object) {
        if (Objects.isNull(object)) {
            throw new JsonSerializationException("Can't serialize a null object");
        }

        Class<?> clazz = object.getClass();
        if (!clazz.isAnnotationPresent(JsonSerializable.class)) {
            throw new JsonSerializationException("The class " + clazz.getSimpleName() + " is not annotated with JsonSerializable");
        }
    }

    private void initializeObject(Object object) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> clazz = object.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Init.class)) {
                method.setAccessible(true);
                method.invoke(object);
            }
        }
    }

    private String getJsonString(Object object) throws IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Map<String, String> jsonElementsMap = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(JsonElement.class)) {
                jsonElementsMap.put(getKey(field), (String) field.get(object));
            } else if(field.isAnnotationPresent(JsonArray.class)){
                StringBuilder jsonArray = new StringBuilder("[");
                Map<String, String> map = (Map<String, String>) field.get(object);
                if(map == null)
                    continue;
                jsonArray.append(
                        map.entrySet()
                                .stream()
                                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                                .collect(Collectors.joining(","))
                );
                jsonArray.append("]");

                String k = getKey(field);
                String v = jsonArray.toString();
                jsonElementsMap.put(k, v);
            }
        }

        String jsonString = jsonElementsMap.entrySet()
                .stream()
                .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                .collect(Collectors.joining(","));
        return "{" + jsonString + "}";
    }

    private String getKey(Field field) {
        String value = null;
        if (field.isAnnotationPresent(JsonElement.class)) {
            value = field.getAnnotation(JsonElement.class)
                    .key();
        } else if(field.isAnnotationPresent(JsonArray.class)){
            value = field.getAnnotation(JsonArray.class)
                    .key();
        }
        
        return value.isEmpty() ? field.getName() : value;
    }
}
