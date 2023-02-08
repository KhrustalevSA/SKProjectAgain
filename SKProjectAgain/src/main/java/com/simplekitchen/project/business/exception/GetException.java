package com.simplekitchen.project.business.exception;

/**
 * класс ошибок получения элементов
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class GetException extends BaseException{
    public GetException(String message) {
        super(message);
    }

    public GetException(String message, Throwable cause) {
        super(message, cause);
    }
}
