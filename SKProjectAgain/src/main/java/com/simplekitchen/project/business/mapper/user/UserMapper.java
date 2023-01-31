package com.simplekitchen.project.business.mapper.user;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * интерфейс создающий маппер преобразования ДТО пользователя в ДАО
 */
@Mapper
public interface UserMapper {

    /**
     * переменная - экземпляр класса
     */
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    /**
     * метод преобразующий ДТО сущность пользователя в ДАО сущность
     * @param daoUser
     * @return daoUser
     */
    UserImpl map(com.simplekitchen.project.dao.entity.user.UserImpl daoUser);

    /**
     * метод преобразующий ДАО сущность пользователя в ДТО сущность
     * @param dtoUser
     * @return daoUser
     */
    com.simplekitchen.project.dao.entity.user.UserImpl map(UserImpl dtoUser);

    /**
     * метод преобразования класса города к интерфейсу города
     * @param daoCity
     * @return com.simplekitchen.project.dto.entity.city.api.City
     */
    @Mapping(source = "cityName.cityName" , target = "cityName")
    City map(CityImpl daoCity);

}
