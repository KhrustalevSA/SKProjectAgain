package com.simplekitchen.project.dto.entity.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplekitchen.project.dto.common.StatusImpl;
import com.simplekitchen.project.dto.entity.user.api.User;
import com.simplekitchen.project.dto.entity.user.api.UserResponseInfo;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс для получения найденных из запроса поьзователей
 * @author KhrustalevSA
 * @since 16.10.2022
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseInfoImpl implements UserResponseInfo {

    /**
     * поле статуса запроса
     */
    @JsonProperty
    private StatusImpl status;

    /**
     * поле списка пользователей
     */
    @JsonProperty
    private List<User> userList;

    public StatusImpl getStatus() {
        return status;
    }

    public List<User> getUserList() {
        if (userList == null) {
            userList = new ArrayList<>();
        }
        return userList;
    }
}
