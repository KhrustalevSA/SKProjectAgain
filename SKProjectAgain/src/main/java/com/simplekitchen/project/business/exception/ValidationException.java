package com.simplekitchen.project.business.exception;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserRequestInfo
 * @since 21.02.2023
 * @author KhrustalevSA
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ValidationException extends BaseException {

    /**
     * Конструктор наследующий конструктор класса BaseException
     * @param message сообщение с информацие об ошибке
     */
    public ValidationException(String message){
        super(message);
    }

    /**
     * Констурктор наслежующий функционал конструктора класса BaseException
     * @param message сообщение с информацие об ошибке
     * @param cause сообщение с ифнормацией почему произошла ошибка
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}



