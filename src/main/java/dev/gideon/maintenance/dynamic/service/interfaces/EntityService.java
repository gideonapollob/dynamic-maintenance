package dev.gideon.maintenance.dynamic.service.interfaces;

public interface EntityService<T> {
    T save(T entity);
}
