package com.simplekitchen.project.dao.entity.common;

import com.simplekitchen.project.dao.entity.image.ImageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController extends AbstractController<ImageImpl, ImageService> {

    public ImageController(ImageService service) {
        super(service);
    }


}
