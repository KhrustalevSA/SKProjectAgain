package com.simplekitchen.project.business.utils;

import com.simplekitchen.project.business.exception.ValidationException;

/**
 * интерфейс валидатора для запросов
 * @param <Rq> тип класса запроса
 */
public interface RequestValidator<Rq> {

    /**
     * метод валидации запроса
     * @param request запрос
     * @throws ValidationException ошибка валидации
     */
    void validate(Rq request) throws ValidationException;
}
