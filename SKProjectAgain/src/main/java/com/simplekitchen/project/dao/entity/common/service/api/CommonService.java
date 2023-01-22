package com.simplekitchen.project.dao.entity.common.service.api;

import com.simplekitchen.project.dao.entity.common.entity.AbstractEntity;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    Optional<E> save(E entity);
//какое-то количество нужных нам методов
}