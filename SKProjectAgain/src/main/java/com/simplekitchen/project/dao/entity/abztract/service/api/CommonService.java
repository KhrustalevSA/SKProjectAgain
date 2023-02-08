package com.simplekitchen.project.dao.entity.abztract.service.api;

import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    Optional<E> save(E entity);
//какое-то количество нужных нам методов
}