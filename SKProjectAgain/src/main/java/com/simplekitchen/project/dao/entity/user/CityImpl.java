package com.simplekitchen.project.dao.entity.user;

import com.simplekitchen.project.dao.entity.user.api.City;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityImpl implements City {

    @Id
    @Column
    private Long uuid;

    /**
     * @return имя города
     * */
    @Column
    private String cityName;

    /**
     * @return имя области
     * */
    @Column
    private String regionName;

    /**
     * @return название улицы
     * */
    @Column
    private String streetName;

    /**
     * @return номер дома
     * */
    @Column
    private Long houseNumber;

    /**
     * @return номер подъезда
     * */
    @Column
    private Long entranceNumber;

    /**
     * @return номер квартиры
     * */
    @Column
    private Long flatNumber;

    @OneToMany(mappedBy = "city")
    private List<UserImpl> userList;
}
