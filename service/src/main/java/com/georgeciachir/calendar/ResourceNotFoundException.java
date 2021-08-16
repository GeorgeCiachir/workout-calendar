package com.georgeciachir.calendar;

public class ResourceNotFoundException extends RuntimeException {

    private static final String RESOURCE_NOT_FOUND = "The %s with id %s was not found";

    public <T> ResourceNotFoundException(Class<T> clazz, String id) {
        super(String.format(RESOURCE_NOT_FOUND, clazz.getSimpleName(), id));
    }
}
