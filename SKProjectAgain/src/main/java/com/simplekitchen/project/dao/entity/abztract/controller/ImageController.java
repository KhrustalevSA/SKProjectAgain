package com.simplekitchen.project.dao.entity.abztract.controller;

import com.simplekitchen.project.dao.entity.abztract.service.ImageService;
import com.simplekitchen.project.dao.entity.image.ImageEntityImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController extends AbstractController<ImageEntityImpl, ImageService> {

    public ImageController(ImageService service) {
        super(service);
    }


}
