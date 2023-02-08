package com.simplekitchen.project.business.exception;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserResponseInfo
 */
@EqualsAndHashCode(callSuper = true)
@Slf4j
public class ResponseInfoNotFoundException extends BaseException {

    public ResponseInfoNotFoundException(String message) {
        super(message);
    }

    public ResponseInfoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
