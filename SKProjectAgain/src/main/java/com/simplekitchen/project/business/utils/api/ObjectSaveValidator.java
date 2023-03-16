package com.simplekitchen.project.business.utils.api;

import com.simplekitchen.project.business.exception.ValidationException;
import com.simplekitchen.project.dto.entity.user.api.User;

/**
 * интерфейс валидатора для объекта пользователя
 */
public interface ObjectSaveValidator<ObjectClass> {

    /**
     * метод валидации объекта сохранения
     * @param savedObject объект для сохранения
     * @throws ValidationException ошибка валидации
     */
    void validate(ObjectClass savedObject) throws ValidationException;
}
