package com.simplekitchen.project.business.exception;

/**
 * класс ошибки сохранения элемента
 * @author KhrustalevSA
 * @since 08.02.2023
 */
public class SaveException extends BaseException{
    public SaveException(String message) {
        super(message);
    }

    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
