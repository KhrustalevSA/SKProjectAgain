package com.simplekitchen.project.business.exception;

import com.simplekitchen.project.business.entity.user.api.UserResponseInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

/**
 * класс ошибки для UserResponseInfo
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Slf4j
public class UserResponseInfoNotFoundException extends Exception {

    public UserResponseInfoNotFoundException(String message){
        super(message);
    }

}
