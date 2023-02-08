package com.simplekitchen.project.dao.entity.abztract.service;

import com.simplekitchen.project.dao.entity.abztract.repository.ImageRepository;
import com.simplekitchen.project.dao.entity.image.ImageEntityImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImageService extends AbstractService<ImageEntityImpl, ImageRepository> {

    public ImageService(ImageRepository repository) {
        super(repository);
    }

    public Optional<ImageEntityImpl> save(ImageEntityImpl image) {
        return Optional.of(repository.save(image));
    }

    public Optional<ImageEntityImpl> find(Long id) {return repository.findById(id);}
}