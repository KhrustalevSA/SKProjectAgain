package com.simplekitchen.project.dao.entity.image;

import com.simplekitchen.project.dao.entity.common.AbstractEntity;
import com.simplekitchen.project.dao.entity.image.api.Image;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "Images")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class ImageImpl extends AbstractEntity implements Image {


    @Column
    private String path;

    @Column
    private String url;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "imagesList")
    private List<RecipeImpl> recipe;
}
