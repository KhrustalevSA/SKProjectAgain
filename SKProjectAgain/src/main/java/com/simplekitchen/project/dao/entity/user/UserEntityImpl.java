package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.entityStatus.EntityStatus;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.role.RoleImpl;
import com.simplekitchen.project.dao.entity.role.api.Role;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@Table
public class UserEntityImpl implements UserEntity, Serializable {

    /**
     * поле уникального идентификатора пользователя
     * */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * уникальный login пользователя
     */
    @Column
    private String username;

    @Enumerated(EnumType.STRING)
    private EntityStatus status;

    @Column
    private String email;

    @Column
    private String password;

    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<RoleImpl> roleList;

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
    private List<RecipeEntityImpl> favoriteRecipeList;

    /**
     * город, место жительства пользователя
     * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private CityEntityImpl city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntityImpl)) return false;
        UserEntityImpl user = (UserEntityImpl) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getName(), user.getName()) && Objects.equals(getSurname(), user.getSurname()) && Objects.equals(getPatronymic(), user.getPatronymic()) && Objects.equals(getBirthDate(), user.getBirthDate()) && Objects.equals(getSex(), user.getSex()) && Objects.equals(getFavoriteRecipeList(), user.getFavoriteRecipeList()) && Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPatronymic(), getBirthDate(), getSex(), getFavoriteRecipeList(), getCity());
    }

    @Override
    public EntityStatus getEntityStatus() {
        return null;
    }

    @Override
    public Calendar getLastPasswordResetDate() {
        return null;
    }
}
