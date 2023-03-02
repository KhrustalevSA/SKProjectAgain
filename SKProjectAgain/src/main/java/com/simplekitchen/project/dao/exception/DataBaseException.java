package com.simplekitchen.project.dao.exception;

/**
 * класс ошибки базы данных
 * @author KhrustalevSA
 * @since 02.03.2023
 */
public class DataBaseException extends Throwable {

    /**
     * конструктор ошибки c сообщением об ошибке
     * @param message сообщение об ошибке
     */
    public DataBaseException(String message) {
        super(message);
    }

    /**
     * конструктор ошибки с сообщением об ошибке и сообщением изза чего возникла ошибка
     * @param message сообщение об ошибке
     * @param cause почему вызвана ошибка
     */
    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
