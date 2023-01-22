package com.simplekitchen.project.dao.entity.user;

import javax.persistence.Embeddable;

@Embeddable
public class UserCityId {
    private Long userId;
    private Long cityId;
}
