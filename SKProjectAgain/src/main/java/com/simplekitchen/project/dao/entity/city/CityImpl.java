package com.simplekitchen.project.dao.entity.city;

import com.simplekitchen.project.dao.entity.city.api.City;
import com.simplekitchen.project.dao.entity.user.UserImpl;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "city")
@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@RequiredArgsConstructor
public class CityImpl implements City {

    /**
     * уникальный идентификатор города
     */
    @Id
    @Column
    @GeneratedValue
    private Long id;

    /**
     * название города
     * */
    @Column
    private String cityName;

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
     *
     * */
    @OneToOne(mappedBy = "city")
    private UserImpl user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CityImpl city = (CityImpl) o;
        return id != null && Objects.equals(id, city.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
