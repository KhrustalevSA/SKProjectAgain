package com.simplekitchen.project.dao.entity.abztract.controller;

import com.simplekitchen.project.dao.entity.abztract.controller.api.CommonController;
import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;
import com.simplekitchen.project.dao.entity.abztract.service.api.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityNotFoundException;


public abstract class AbstractController<E extends AbstractEntity, S extends CommonService<E>> implements CommonController<E> {

    private final S service;

    @Autowired
    protected AbstractController(S service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<E> save(@RequestBody E entity) {
        return service.save(entity).map(ResponseEntity::ok)
                .orElseThrow( EntityNotFoundException::new);
    }

//другие методы
}
