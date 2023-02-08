package com.simplekitchen.project.dao.entity.abztract.repository;

import com.simplekitchen.project.dao.entity.abztract.entity.AbstractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface CommonRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {
}
