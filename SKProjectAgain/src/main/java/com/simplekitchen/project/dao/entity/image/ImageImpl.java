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
public class ImageImpl extends AbstractEntity implements Image {

    @Column
    private String path;

    @Column
    private String url;

    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "imagesList")
    private List<RecipeImpl> recipe;

    public ImageImpl(Long id) {
        super(id);
    }

    public ImageImpl(Long id, String path, String url, List<RecipeImpl> recipe) {
        super(id);
        this.path = path;
        this.url = url;
        this.recipe = recipe;
    }

    public ImageImpl() {
    }

    public ImageImpl(String path) {
        this.path = path;
    }

    public ImageImpl(String path, String url, List<RecipeImpl> recipe) {
        this.path = path;
        this.url = url;
        this.recipe = recipe;
    }

    @Override
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public List<RecipeImpl> getRecipe() {
        return recipe;
    }

    public void setRecipe(List<RecipeImpl> recipe) {
        this.recipe = recipe;
    }

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

    @Override
    public String toString() {
        return "ImageImpl{" +
                "path='" + path + '\'' +
                ", url='" + url + '\'' +
                ", recipe=" + recipe +
                '}';
    }

    public static ImageImpl.ImageImplBuilder builder() {return new ImageImpl.ImageImplBuilder();}

    public static class ImageImplBuilder {
        private String path;
        private String url;
        private List<RecipeImpl> recipe;

        ImageImplBuilder() {
        }

        public ImageImpl.ImageImplBuilder path(final String path) {
            this.path = path;
            return this;
        }

        public ImageImpl.ImageImplBuilder url(final String url) {
            this.url = url;
            return this;
        }

        public ImageImpl.ImageImplBuilder recipe(final List<RecipeImpl> recipe) {
            this.recipe = recipe;
            return this;
        }

        public ImageImpl build() {
            return new ImageImpl(this.path, this.url, this.recipe);
        }

        public String toString() {
            return "ImageImpl.ImageImplBuilder(path=" + this.path + ", url=" + this.url + ", recipe=" + this.recipe + ")";
        }
    }
}
