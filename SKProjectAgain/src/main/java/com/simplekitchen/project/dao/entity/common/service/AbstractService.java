package com.simplekitchen.project.dao.entity.common.service;

import com.simplekitchen.project.dao.entity.common.entity.AbstractEntity;
import com.simplekitchen.project.dao.entity.common.repository.CommonRepository;
import com.simplekitchen.project.dao.entity.common.service.api.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

//другие методы, переопределённые из интерфейса
}
