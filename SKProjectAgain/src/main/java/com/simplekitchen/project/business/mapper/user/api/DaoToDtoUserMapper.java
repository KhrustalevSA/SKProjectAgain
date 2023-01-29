package com.simplekitchen.project.business.mapper.user.api;

import com.simplekitchen.project.dao.entity.city.CityImpl;
import com.simplekitchen.project.dao.entity.city.CityNameImpl;
import com.simplekitchen.project.dto.entity.city.api.City;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

/**
 * интерфейс создающий маппер преобразования ДТО пользователя в ДАО
 */
@Mapper
public interface DaoToDtoUserMapper {

    /**
     * переменная - экземпляр класса
     */
    DaoToDtoUserMapper INSTANCE = Mappers.getMapper(DaoToDtoUserMapper.class);

    /**
     * метод преобразующий ДТО сущность пользователя в ДАО сущность
     * @param daoUser
     * @return daoUser
     */
    UserImpl DaoToDtoUser(com.simplekitchen.project.dao.entity.user.UserImpl daoUser);

    /**
     * метод преобразующий ДАО сущность пользователя в ДТО сущность
     * @param dtoUser
     * @return daoUser
     */
    com.simplekitchen.project.dao.entity.user.UserImpl DtoToDaoUser(UserImpl dtoUser);

    /**
     * метод преобразования класса корода к интерфейс города
     * @param daoCity
     * @return
     */
    default City map(CityImpl daoCity) {
        return (City) daoCity;
    }

    /**
     * метод преобразования строки имени города к сущности имени города
     * @param cityName
     * @return new CityNameImpl
     */
    default CityNameImpl map(String cityName) {
        return CityNameImpl.builder().cityName(cityName).build();
    }

    default List<com.simplekitchen.project.dao.entity.user.UserImpl> map(List<UserImpl> userList) {
        List<com.simplekitchen.project.dao.entity.user.UserImpl> returnedUsers = new ArrayList<>();
        for (UserImpl user : userList) {
            returnedUsers.add(DaoToDtoUserMapper.INSTANCE.DtoToDaoUser(user));
        }
        return returnedUsers;
    }

}
