package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import com.simplekitchen.project.dao.entity.user.api.User;
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
public interface UserRepository extends CrudRepository<User, Long> {
    public Optional<List<User>> findByName(String name);
    public Optional<List<User>> findBySurname(String surname);
    public List<User> findByNameAndSurname(String name, String surname);
}
