package com.simplekitchen.project.dao.service.api;

public interface Service<T> {
    void add(T value);
    T get(T value, Long id);
    void addOrUpdate(T value);
    void Update(T value);
    T find(long id);
    void delete(T value, Long id);
}
