package com.simplekitchen.project.dao.entity.abztract.controller.api;

import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommonController<E extends AbstractEntity> {

    @PostMapping
    ResponseEntity<E> save(@RequestBody E entity);

//остальные методы
}
