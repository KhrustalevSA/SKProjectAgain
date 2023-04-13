package com.simplekitchen.project.dto.security;

import lombok.Data;

@Data
public class AuthenticationRequestDto {
    private String username;
    private String password;
}
