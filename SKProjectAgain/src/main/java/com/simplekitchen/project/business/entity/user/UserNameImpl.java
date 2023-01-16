package com.simplekitchen.project.business.entity.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserNameImpl {
    private String name;

    /**
     * Метод для получения фамилии пользователя
     * */
    private String surname;

    /**
     * Метод для получения отчества пользователя
     * */
    private String patronymic;
}
