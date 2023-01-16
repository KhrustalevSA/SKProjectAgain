package com.simplekitchen.project.dao.entity.common;

import com.simplekitchen.project.dao.entity.image.ImageImpl;
import com.simplekitchen.project.dao.repository.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService extends AbstractService<ImageImpl, ImageRepository> {

    public ImageService(ImageRepository repository) {
        super(repository);
    }

    public Optional<ImageImpl> save(ImageImpl image) {
        return Optional.of(repository.save(image));
    }
}