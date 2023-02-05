package com.simplekitchen.project.dao.exception;

public class DataBaseException extends Throwable{

    /**
     *
     * @param message
     */
    public DataBaseException(String message) {
        super(message);
    }

    /**
     *
     * @param message
     * @param cause
     */
    public DataBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
