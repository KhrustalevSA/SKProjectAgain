package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * класс сущности пользователя
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Entity
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserImpl implements User, Serializable {

    /**
     * поле уникального идентификатора пользователя
     * */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * имя пользователя
     * */
    @Column
    private String name;

    /**
     * фамилия пользователя
     * */
    @Column
    private String surname;

    /**
     * отчество пользователя
     * */
    @Column
    private String patronymic;

    /**
     * дата рождения пользователя
     * */
    @Column
    private Calendar birthDate;

    /**
     * пол пользователя
     * */
    @Column
    private String sex;

    /**
     * список рецептов добавленных в "избранное"
     * */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_recipes",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_id"))
    private List<RecipeImpl> favoriteRecipeList;

    /**
     * город, место жительства пользователя
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
