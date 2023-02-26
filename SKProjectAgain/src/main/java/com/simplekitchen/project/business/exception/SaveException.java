package com.simplekitchen.project.business.exception;

/**
 * класс ошибки сохранения элемента
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class SaveException extends BaseException{

    /**
     * Конструктор наследующий конструктор класса BaseException
     * @param message сообщение с информацие об ошибке
     */
    public SaveException(String message) {
        super(message);
    }

    /**
     * Констурктор наслежующий функционал конструктора класса BaseException
     * @param message сообщение с информацие об ошибке
     * @param cause сообщение с ифнормацией почему произошла ошибка
     */
    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }
}



