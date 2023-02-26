package com.simplekitchen.project.business.exception;

/**
 * класс ошибок получения элементов
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class GetException extends BaseException{

    /**
     * Конструктор наследующий конструктор класса BaseException
     * @param message сообщение с информацие об ошибке
     */
    public GetException(String message) {
        super(message);
    }

    /**
     * Констурктор наслежующий функционал конструктора класса BaseException
     * @param message сообщение с информацие об ошибке
     * @param cause сообщение с ифнормацией почему произошла ошибка
     */
    public GetException(String message, Throwable cause) {
        super(message, cause);
    }
}



