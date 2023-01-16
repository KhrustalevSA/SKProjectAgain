package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.common.CommonRepository;
import com.simplekitchen.project.dao.entity.image.ImageImpl;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends CommonRepository<ImageImpl> {
}


