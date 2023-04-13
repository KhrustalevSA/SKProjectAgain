package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * интерфейс репозитория пользователей
 * CRUD репозиторий
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntityImpl, Long>  {

    Optional<List<UserEntity>> findByNameAndSurname(String name, String surname);

    /**
     * метод поиска пользователя по имени и фамилии
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return Optional объект списка найденных пользователей
     */
    Optional<List<UserEntity>> findByNameAndSurnameAndPatronymic(String name, String surname, String patronymic);

    UserEntity findByUsername(String username);
}
