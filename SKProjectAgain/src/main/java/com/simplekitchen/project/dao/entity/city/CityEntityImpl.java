package com.simplekitchen.project.dao.entity.city;

import com.simplekitchen.project.dao.entity.city.api.CityEntity;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * класс ДАО сущности города
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Entity
@Table(name = "city")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CityEntityImpl implements CityEntity {

    /**
     * уникальный идентификатор города
     */
    @Id
    @Column
    @GeneratedValue
    private Long id;

    /**
     * название области
     * */
    @Column
    private String regionName;

    /**
     * название улицы
     * */
    @Column
    private String streetName;

    /**
     * номер дома
     * */
    @Column
    private Long houseNumber;

    /**
     * номер подъезда
     * */
    @Column
    private Long entranceNumber;

    /**
     * номер квартиры
     * */
    @Column
    private Long flatNumber;


    /**
     * пользователь для которого создается экземпляр данного класса
     * */
    @OneToOne(mappedBy = "city")
    private UserEntityImpl user;

    /**
     * название города
     * */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "city_name", referencedColumnName = "id")
    private CityNameEntityImpl cityName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityEntityImpl city = (CityEntityImpl) o;
        return id != null && Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
