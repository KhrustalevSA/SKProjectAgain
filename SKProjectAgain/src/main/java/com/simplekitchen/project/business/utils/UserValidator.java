package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;

/**
 * интерфейс валидатора для объекта пользователя
 * @param <U> класс пользователя
 */
public interface UserValidator<U> {

    /**
     * метод валидации объекта пользователя
     * @param user объект пользователя
     * @throws ValidationException ошибка валидации
     */
    void validate(U user) throws ValidationException;
}
