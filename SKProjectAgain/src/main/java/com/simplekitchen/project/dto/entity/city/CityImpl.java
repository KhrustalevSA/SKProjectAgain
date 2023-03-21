package com.simplekitchen.project.dto.entity.city;

import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ДТО класс города
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityImpl implements City {

    /**
     * уникальный идентификатор города
     */
    private Long id;

    /**
     * название города
     */
    private String cityName;

    /**
     * название области
     */
    private String regionName;

    /**
     * название улицы
     */
    private String streetName;

    /**
     * номер дома
     */
    private Long houseNumber;

    /**
     * номер подъезда
     */
    private Long entranceNumber;

    /**
     * номер квартиры
     */
    private Long flatNumber;

    private UserImpl user;
}