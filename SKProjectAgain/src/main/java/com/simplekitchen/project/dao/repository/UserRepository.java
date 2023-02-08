package com.simplekitchen.project.dao.repository;

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
public interface UserRepository extends CrudRepository<UserEntity, Long>  {
    Optional<List<UserEntity>> findByName(String name);
    Optional<List<UserEntity>> findBySurname(String surname);
    List<UserEntity> findByNameAndSurname(String name, String surname);
    void deleteAllByNameAndSurname(String name, String surname);
    void deleteAllById(List<Long> longList);
}
