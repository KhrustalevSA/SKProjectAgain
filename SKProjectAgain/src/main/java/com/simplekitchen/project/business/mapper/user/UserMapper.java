package com.simplekitchen.project.business.mapper.user;

import com.simplekitchen.project.business.entity.user.UserListImpl;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dao.entity.user.api.UserList;
import com.simplekitchen.project.dto.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
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
     * метод преобразующий ДАО сущность пользователя в ДТО сущность
     * @param daoUser сущность польщователя
     * @return daoUser
     */
    UserImpl map(UserEntity daoUser);

    /**
     * метод преобразующий ДТО сущность пользователя в ДАО сущность
     * @param dtoUser ДТО пользователь
     * @return daoUser
     */
    UserEntityImpl map(User dtoUser);

    /**
     * метод преобразования списков пользователей
     * @param userList список пользователей бизнесс слоя
     * @return ДАО список пользователей
     */
    UserListImpl map(com.simplekitchen.project.business.entity.user.api.UserList userList);

    /**
     * метод преобразования списков пользователей
     * @param userList список пользователей ДАО слоя
     * @return бизнесс список пользователей
     */
    @Mapping(target = "userList", source = "userEntityList")
    com.simplekitchen.project.business.entity.user.UserListImpl map (UserList userList);

    /**
     * метод преобразования класса города к интерфейсу города
     * @param daoCity ДАО сущность города
     * @return ДТО город
     */
    @Mapping(target = "cityName", source = "cityName.cityName")
    CityImpl map(CityEntityImpl daoCity);

    /**
     * метод преобразования строки с названием города к ДАО сщности с названиями городов
     * @param cityName название города
     * @return ДАО сущность города
     */
    @Mapping(target = "cityName")
    CityNameEntityImpl map(String cityName);


}
