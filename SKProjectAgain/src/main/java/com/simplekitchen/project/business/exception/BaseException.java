package com.simplekitchen.project.business.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Общий класс ошибок работы сервисов
 * @since 08.02.2023
 * @author KhrustalevSA
 */
@Slf4j
public class BaseException extends Throwable {

    public BaseException(String message) {
        super(message);
    }


    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
