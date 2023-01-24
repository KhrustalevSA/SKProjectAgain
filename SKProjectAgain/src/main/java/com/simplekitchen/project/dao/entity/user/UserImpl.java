package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
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
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserImpl user = (UserImpl) o;
        return id != null && Objects.equals(id, user.id);
    }

    public UserImpl() {
    }

    public UserImpl(String name, String surname, String patronymic) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
    }

    public UserImpl(String name, String surname, String patronymic, Calendar birthDate, String sex, List<RecipeImpl> favoriteRecipeList, CityImpl city) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.favoriteRecipeList = favoriteRecipeList;
        this.city = city;
    }

    public UserImpl(Long id, String name, String surname, String patronymic, Calendar birthDate, String sex, List<RecipeImpl> favoriteRecipeList, CityImpl city) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        this.sex = sex;
        this.favoriteRecipeList = favoriteRecipeList;
        this.city = city;
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
    public Calendar getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Calendar birthDate) {
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

    @Override
    public String toString() {
        return "UserImpl{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", birthDate=" + birthDate +
                ", sex='" + sex + '\'' +
                ", favoriteRecipeList=" + favoriteRecipeList +
                ", city=" + city +
                '}';
    }

    public static UserImpl.UserImplBuilder builder() {
        return new UserImpl.UserImplBuilder();
    }

    public static class UserImplBuilder {
        private Long id;
        private String name;
        private String surname;
        private String patronymic;
        private Calendar birthDate;
        private String sex;
        private List<RecipeImpl> favoriteRecipeList;
        private CityImpl city;

        UserImplBuilder() {
        }

        public UserImpl.UserImplBuilder id(final Long id) {
            this.id = id;
            return this;
        }

        public UserImpl.UserImplBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserImpl.UserImplBuilder surname(final String surname) {
            this.surname = surname;
            return this;
        }

        public UserImpl.UserImplBuilder patronymic(final String patronymic) {
            this.patronymic = patronymic;
            return this;
        }

        public UserImpl.UserImplBuilder birthDate(final Calendar birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserImpl.UserImplBuilder sex(final String sex) {
            this.sex = sex;
            return this;
        }

        public UserImpl.UserImplBuilder favoriteRecipeList(final List<RecipeImpl> favoriteRecipeList) {
            this.favoriteRecipeList = favoriteRecipeList;
            return this;
        }

        public UserImpl.UserImplBuilder city(final CityImpl city) {
            this.city = city;
            return this;
        }

        public UserImpl build() {
            return new UserImpl(this.id, this.name, this.surname, this.patronymic, this.birthDate, this.sex, this.favoriteRecipeList, this.city);
        }

        public String toString() {
            return "UserImpl.UserImplBuilder(id=" + this.id + ", name=" + this.name + ", surname=" + this.surname + ", patronymic=" + this.patronymic + ", birthDate=" + this.birthDate + ", sex=" + this.sex + ", favoriteRecipeList=" + this.favoriteRecipeList + ", city=" + this.city + ")";
        }
    }
}
