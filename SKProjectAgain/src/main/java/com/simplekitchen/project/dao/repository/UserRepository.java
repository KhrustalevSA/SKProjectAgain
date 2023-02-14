package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserEntityImpl;
import com.simplekitchen.project.dao.entity.user.api.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    Optional<List<UserEntityImpl>> findByName(String name);
    Optional<List<UserEntityImpl>> findBySurname(String surname);
    Optional<List<UserEntityImpl>> findByNameAndSurname(String name, String surname);
    void deleteAllByNameAndSurname(String name, String surname);
}
