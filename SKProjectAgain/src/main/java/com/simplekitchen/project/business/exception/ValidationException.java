package com.simplekitchen.project.business.exception;

import com.simplekitchen.project.business.entity.user.api.UserRequestInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserRequestInfo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class ValidationException extends Throwable{
    public ValidationException(String message){
        super(message);
    }
}
