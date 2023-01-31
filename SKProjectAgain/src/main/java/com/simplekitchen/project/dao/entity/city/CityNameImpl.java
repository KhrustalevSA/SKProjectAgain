package com.simplekitchen.project.dao.entity.city;

import com.simplekitchen.project.dao.entity.city.api.City;
import com.simplekitchen.project.dao.entity.city.api.CityName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * ДАО сущность имен городов
 * отдельная таблица для хранения названий городов
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CityNameImpl implements CityName {

    /**
     * уникальный идентификатор имени города
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * поле имени города
     */
    @Column
    private String cityName;

    /**
     * связь с сущностью города для пользователя
     */
    @OneToOne(mappedBy = "cityName")
    private CityImpl city;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityNameImpl cityName = (CityNameImpl) o;
        return id != null && Objects.equals(id, cityName.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
