package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserImpl implements User {
    /**
     * Метод для получения имени пользователя
     * */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Метод для получения имени пользователя
     * */
    @Column
    private String name;

    /**
     * Метод для получения фамилии пользователя
     * */
    @Column
    private String surname;

    /**
     * Метод для получения отчества пользователя
     * */
    @Column
    private String patronymic;

    /**
     * Метод для получения даты рождения пользователя
     * */
    @Column
    private Date birthDate;

    /**
     * Метод для получения пола пользователя
     * */
    @Column
    private String sex;

    /**
     * Метод для получения списка любимых рецептов пользователя
     * */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeImpl> favoriteRecipeList;

    /**
     * Метод для получения места жительства пользователя
     * */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    @ToString.Exclude
    private CityImpl city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserImpl user = (UserImpl) o;
        return id != null && Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Override
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    public List<RecipeImpl> getFavoriteRecipeList() {
        return favoriteRecipeList;
    }

    public void setFavoriteRecipeList(List<RecipeImpl> favoriteRecipeList) {
        this.favoriteRecipeList = favoriteRecipeList;
    }

    @Override
    public CityImpl getCity() {
        return city;
    }

    public void setCity(CityImpl city) {
        this.city = city;
    }
}
