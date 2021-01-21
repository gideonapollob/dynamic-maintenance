package dev.gideon.maintenance.dynamic.service.impl;

import dev.gideon.maintenance.dynamic.service.interfaces.EntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

abstract class EntityServiceImpl<E, R extends JpaRepository> implements EntityService<E> {
    @Autowired
    protected R repository;

    @Override
    public E save(E entity) {
        return (E) repository.save(entity);
    }
}
