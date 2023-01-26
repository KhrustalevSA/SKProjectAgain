package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserImpl implements User, Serializable {
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
    private Calendar birthDate;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityImpl city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserImpl)) return false;
        UserImpl user = (UserImpl) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getPatronymic(), user.getPatronymic()) && Objects.equals(getBirthDate(), user.getBirthDate()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getFavoriteRecipeList(), user.getFavoriteRecipeList()) && Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPatronymic(), getBirthDate(), getSex(), getFavoriteRecipeList(), getCity());
    }
}
