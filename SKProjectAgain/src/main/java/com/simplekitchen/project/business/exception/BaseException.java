package com.simplekitchen.project.business.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Общий класс ошибок работы сервисов
 * @since 08.02.2023
 * @author KhrustalevSA
 */
@Slf4j
public class BaseException extends Throwable {

    /**
     * Конструктор наследующий конструктор класса Throwable
     * @param message сообщение с информацие об ошибке
     */
    public BaseException(String message) {
        super(message);
    }

    /**
     * Констурктор наслежующий функционал конструктора класса Throwable
     * @param message сообщение с информацие об ошибке
     * @param cause сообщение с ифнормацией почему произошла ошибка
     */
    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}