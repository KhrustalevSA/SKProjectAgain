package com.simplekitchen.project.dao.entity.common;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {

    Optional<E> save(E entity);
//какое-то количество нужных нам методов
}