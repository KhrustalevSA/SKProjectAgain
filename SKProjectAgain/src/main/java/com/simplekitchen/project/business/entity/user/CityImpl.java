package com.simplekitchen.project.business.entity.user;

import com.simplekitchen.project.business.entity.user.api.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Класс реализовывающий интерфейс City
 * @see
 * @Author KhrustalevSA
 * @since 28.09.2022
 * */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityImpl implements City {

    /**
     * поле уникального идентификатора города пользователя
     */
    private Long uuid;

    /**
     * поле имени города
     * */
    private String cityName;

    /**
     * поле имени области
     * */
    private String regionName;

    /**
     * поле названия улицы
     * */
    private String streetName;

    /**
     * поле номера дома
     * */
    private Long houseNumber;

    /**
     * поле номера подъезда
     * */
    private Long entranceNumber;

    /**
     * поле номера квартиры
     * */
    private Long flatNumber;
}
