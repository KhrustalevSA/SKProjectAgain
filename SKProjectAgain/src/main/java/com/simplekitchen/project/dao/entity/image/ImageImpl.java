package com.simplekitchen.project.dao.entity.image;

import com.simplekitchen.project.dao.entity.common.entity.AbstractEntity;
import com.simplekitchen.project.dao.entity.image.api.Image;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Images")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class ImageImpl extends AbstractEntity implements Image {

    @Column
    private String path;

    @Column
    private String url;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "imagesList")
    private List<RecipeImpl> recipe;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ImageImpl)) return false;
        if (!super.equals(o)) return false;
        ImageImpl image = (ImageImpl) o;
        return Objects.equals(getPath(), image.getPath()) && Objects.equals(getUrl(), image.getUrl()) && Objects.equals(getRecipe(), image.getRecipe());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPath(), getUrl(), getRecipe());
    }

}
