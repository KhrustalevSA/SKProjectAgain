package com.simplekitchen.project.business.exception;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserRequestInfo
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ValidationException extends BaseException {
    public ValidationException(String message){
        super(message);
    }
}
