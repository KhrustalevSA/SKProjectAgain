package com.simplekitchen.project.business.utils.api;

import com.simplekitchen.project.business.exception.ValidationException;

/**
 * интерфейс валидатора для запросов
 * @param <Rq> тип класса запроса
 * @author KhrustalevSA
 * @since 16.03.2023
 */
public interface RequestValidator<Rq> {

    /**
     * метод валидации запроса
     * @param request запрос
     * @throws ValidationException ошибка валидации
     */
    void validate(Rq request) throws ValidationException;
}
