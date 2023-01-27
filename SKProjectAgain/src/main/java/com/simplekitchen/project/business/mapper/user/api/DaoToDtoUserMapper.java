package com.simplekitchen.project.business.mapper.user.api;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.city.CityNameImpl;
import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.mapstruct.Mapper;

@Mapper
public interface DaoToDtoUserMapper {
    UserImpl DaoToDtoUser(com.simplekitchen.project.dao.entity.user.UserImpl daoUser);
    com.simplekitchen.project.dao.entity.user.UserImpl DtoToDaoUser(UserImpl dtoUser);

    default City map(CityImpl daoCity) {
        return (City) daoCity;
    }

    default CityNameImpl map(String cityName) {
        return CityNameImpl.builder().cityName(cityName).build();
    }

}
