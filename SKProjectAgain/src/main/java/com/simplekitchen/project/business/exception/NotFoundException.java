package com.simplekitchen.project.business.exception;

import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки на нахождения элемента
 * @author KhrustalevSA
 * @since 08.02.2023
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class NotFoundException extends BaseException {
    /**
     * полученная информация изза которой возникла ошибка
     */
    private UserRequestInfo userRequestInfo;

    public NotFoundException(String message, UserRequestInfo userRequestInfo){
        super(message);
        this.userRequestInfo =userRequestInfo;
    }

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundException(String message, Throwable cause, UserRequestInfo userRequestInfo) {
        super(message, cause);
        this.userRequestInfo = userRequestInfo;
    }
}
