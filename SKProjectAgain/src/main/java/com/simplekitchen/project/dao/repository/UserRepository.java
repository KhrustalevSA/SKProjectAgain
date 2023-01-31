package com.simplekitchen.project.dao.repository;

import com.simplekitchen.project.dao.entity.user.UserImpl;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * интерфейс репозитория пользователей
 * CRUD репозиторий
 * @author KhrustalevSA
 * @since 31.01.2023
 */
@Repository
public interface UserRepository extends CrudRepository<UserImpl, Long> {
}
