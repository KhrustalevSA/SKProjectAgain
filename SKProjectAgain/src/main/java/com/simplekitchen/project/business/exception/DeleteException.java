package com.simplekitchen.project.business.exception;

/**
 * класс ошибок при удалении элементов
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class DeleteException extends BaseException{
    public DeleteException(String message) {
        super(message);
    }

    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}
