package com.simplekitchen.project.business.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки, если не нашелся пользователь
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class UserNotFoundException extends Exception{
    /**
     * уникальзный идентификатор не найденного пользователя
     */
    private Long id;

    public UserNotFoundException(String message, Long id){

        super(message);
        this.id =id;
    }
}
