package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
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

    /**
     * метод поиска пользователя по имени
     * @param name имя пользователя
     * @return Optional объект списка найденных пользователей
     */
    Optional<List<UserEntityImpl>> findByName(String name);

    /**
     * метод поиска пользователя по фамилии
     * @param surname фамилия пользователя
     * @return Optional объект списка найденных пользователей
     */
    Optional<List<UserEntityImpl>> findBySurname(String surname);

    /**
     * метод поиска пользователя по имени и фамилии
     * @param name имя пользователя
     * @param surname фамилия пользователя
     * @return Optional объект списка найденных пользователей
     */
    Optional<List<UserEntityImpl>> findByNameAndSurname(String name, String surname);

    /**
     * метод удаления пользователей по имени и фамилии
     * @param name имя пользователя
     * @param surname фамилия пользователя
     */
    void deleteAllByNameAndSurname(String name, String surname);
}
