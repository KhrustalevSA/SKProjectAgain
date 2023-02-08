package com.simplekitchen.project.dao.entity.abztract.service;

import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;
import com.simplekitchen.project.dao.entity.abztract.repository.CommonRepository;
import com.simplekitchen.project.dao.entity.abztract.service.api.CommonService;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }

//другие методы, переопределённые из интерфейса
}
