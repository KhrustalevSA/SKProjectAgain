package com.simplekitchen.project.business.exception;

import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserRequestInfo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class UserNotFoundException extends Throwable{
    /**
     * полученная информация изза которой возникла ошибка
     */
    private UserRequestInfo userRequestInfo;

    public UserNotFoundException(String message, UserRequestInfo userRequestInfo){
        super(message);
        this.userRequestInfo =userRequestInfo;
    }
}
