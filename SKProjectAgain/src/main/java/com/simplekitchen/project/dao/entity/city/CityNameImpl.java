package com.simplekitchen.project.dao.entity.city;

import com.simplekitchen.project.dao.entity.city.api.City;
import com.simplekitchen.project.dao.entity.city.api.CityName;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CityNameImpl implements CityName {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String cityName;

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
