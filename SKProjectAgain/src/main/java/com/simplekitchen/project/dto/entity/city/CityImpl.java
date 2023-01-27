package com.simplekitchen.project.dto.entity.city;

import com.simplekitchen.project.dto.entity.city.api.City;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CityImpl implements City {

    private Long id;

    private String cityName;

    private String regionName;

    private String streetName;

    private Long houseNumber;

    private Long entranceNumber;

    private Long flatNumber;
}
