package com.simplekitchen.project.dao.entity.common.controller;

import com.simplekitchen.project.dao.entity.common.service.ImageService;
import com.simplekitchen.project.dao.entity.image.ImageImpl;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController extends AbstractController<ImageImpl, ImageService> {

    public ImageController(ImageService service) {
        super(service);
    }


}
