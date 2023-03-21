package com.simplekitchen.project.business.mapper;

import com.simplekitchen.project.business.mapper.user.UserMapper;
import com.simplekitchen.project.dao.entity.city.CityEntityImpl;
import com.simplekitchen.project.dao.entity.city.CityNameEntityImpl;
import com.simplekitchen.project.dao.entity.recipe.RecipeEntityImpl;
import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import com.simplekitchen.project.dto.entity.city.CityImpl;
import com.simplekitchen.project.dto.entity.recipe.RecipeImpl;
import com.simplekitchen.project.dto.entity.user.UserImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class UserMapperTest {

    @Test
    public void mapDaoUserEntityToDtoUserImpl() {
        UserEntity daoUser = UserEntityImpl.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .patronymic("Patronymic")
                .sex("M")
                .birthDate(new GregorianCalendar(2000, Calendar.FEBRUARY, 1))
                .city(CityEntityImpl.builder()
                        .id(1L)
                        .cityName(CityNameEntityImpl.builder().id(1L).cityName("CityName").build())
                        .entranceNumber(1L)
                        .flatNumber(1L)
                        .houseNumber(1L)
                        .streetName("StreetName")
                        .build())
                .favoriteRecipeList(Collections.singletonList(RecipeEntityImpl.builder()
                        .id(1L)
                        .userList(Collections.singletonList(UserEntityImpl.builder()
                                .id(1L)
                                .name("UserName")
                                .build()))
                        .build()))
                .build();

        UserImpl dtoUser;

        dtoUser = UserMapper.INSTANCE.map(daoUser);

        Assert.assertEquals(daoUser.getId(), dtoUser.getId());
        Assert.assertEquals(daoUser.getName(), daoUser.getName());
        Assert.assertEquals(daoUser.getSurname(), dtoUser.getSurname());
        Assert.assertEquals(daoUser.getBirthDate(), dtoUser.getBirthDate());
        Assert.assertEquals(daoUser.getCity().getCityName().getCityName(), dtoUser.getCity().getCityName());
        Assert.assertEquals(
                daoUser.getFavoriteRecipeList().get(0).getUserList().get(0).getName(),
                dtoUser.getFavoriteRecipeList().get(0).getUserList().get(0).getName()
        );
        Assert.assertEquals(daoUser.getPatronymic(), dtoUser.getPatronymic());
        Assert.assertEquals(daoUser.getSex(), dtoUser.getSex());
    }

    @Test
    public void mapDtoUserImplToDaoUserEntity() {
        UserImpl dtoUser = UserImpl.builder()
                .id(1L)
                .name("Name")
                .surname("Surname")
                .patronymic("Patronymic")
                .sex("M")
                .birthDate(new GregorianCalendar(2000, Calendar.FEBRUARY, 1))
                .city(CityImpl.builder()
                        .id(1L)
                        .cityName("CityName")
                        .entranceNumber(1L)
                        .flatNumber(1L)
                        .houseNumber(1L)
                        .streetName("StreetName")
                        .build())
                .favoriteRecipeList(Collections.singletonList(RecipeImpl.builder()
                        .id(1L)
                        .userList(Collections.singletonList(UserImpl.builder()
                                .id(1L)
                                .name("UserName")
                                .build()))
                        .build()))
                .build();
        UserEntityImpl daoUser;

        daoUser = UserMapper.INSTANCE.map(dtoUser);

        Assert.assertEquals(dtoUser.getId(), daoUser.getId());
        Assert.assertEquals(dtoUser.getName(), daoUser.getName());
        Assert.assertEquals(dtoUser.getSurname(), daoUser.getSurname());
        Assert.assertEquals(dtoUser.getBirthDate(), daoUser.getBirthDate());
        Assert.assertEquals(dtoUser.getCity().getCityName(), daoUser.getCity().getCityName().getCityName());
        Assert.assertEquals(
                dtoUser.getFavoriteRecipeList().get(0).getUserList().get(0).getName(),
                daoUser.getFavoriteRecipeList().get(0).getUserList().get(0).getName()
        );
        Assert.assertEquals(dtoUser.getPatronymic(), daoUser.getPatronymic());
        Assert.assertEquals(dtoUser.getSex(), daoUser.getSex());
    }

    @Test
    public void mapDaoCityEntityImplToDtoCityImpl() {
        CityEntityImpl daoCity = CityEntityImpl.builder()
                .id(1L)
                .streetName("StreetName")
                .houseNumber(1L)
                .cityName(CityNameEntityImpl.builder().id(1L).cityName("CityName").build())
                .flatNumber(1L)
                .entranceNumber(1L)
                .user(UserEntityImpl.builder()
                        .id(1L)
                        .name("Name")
                        .surname("Surname")
                        .patronymic("Patronymic")
                        .sex("M")
                        .birthDate(new GregorianCalendar(2000, Calendar.FEBRUARY, 1))
                        .city(CityEntityImpl.builder()
                                .id(1L)
                                .cityName(CityNameEntityImpl.builder().id(1L).cityName("CityName").build())
                                .entranceNumber(1L)
                                .flatNumber(1L)
                                .houseNumber(1L)
                                .streetName("StreetName")
                                .build())
                        .favoriteRecipeList(Collections.singletonList(RecipeEntityImpl.builder()
                                .id(1L)
                                .userList(Collections.singletonList(UserEntityImpl.builder()
                                        .id(1L)
                                        .name("UserName")
                                        .build()))
                                .build()))
                        .build())
                .regionName("RegionName")
                .build();

        CityImpl dtoCity;

        dtoCity = UserMapper.INSTANCE.map(daoCity);

        Assert.assertEquals(daoCity.getId(), dtoCity.getId());
        Assert.assertEquals(daoCity.getStreetName(), dtoCity.getStreetName());
        Assert.assertEquals(daoCity.getHouseNumber(), dtoCity.getHouseNumber());
        Assert.assertEquals(daoCity.getCityName().getCityName(), dtoCity.getCityName());
        Assert.assertEquals(daoCity.getFlatNumber(), dtoCity.getFlatNumber());
        Assert.assertEquals(daoCity.getEntranceNumber(), dtoCity.getEntranceNumber());
        Assert.assertEquals(daoCity.getUser().getName(), dtoCity.getUser().getName());
        Assert.assertEquals(daoCity.getRegionName(), dtoCity.getRegionName());
    }

    @Test
    public void mapStringCityNameToDaoCityEntityNameImpl() {
        String name = "Name";
        CityNameEntityImpl cityName;

        cityName = UserMapper.INSTANCE.map(name);

        Assert.assertEquals(name, cityName.getCityName());
    }
}