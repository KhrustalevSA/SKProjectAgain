package com.simplekitchen.project.business.exception;

/**
 * класс ошибок при удалении элементов
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class DeleteException extends BaseException{

    /**
     * Конструктор наследующий конструктор класса BaseException
     * @param message сообщение с информацие об ошибке
     */
    public DeleteException(String message) {
        super(message);
    }

    /**
     * Констурктор наслежующий функционал конструктора класса BaseException
     * @param message сообщение с информацие об ошибке
     * @param cause сообщение с ифнормацией почему произошла ошибка
     */
    public DeleteException(String message, Throwable cause) {
        super(message, cause);
    }
}




