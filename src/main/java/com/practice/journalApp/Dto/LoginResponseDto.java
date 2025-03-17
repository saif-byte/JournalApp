package com.practice.journalApp.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class LoginResponseDto {
    private String userName;
    private String jwtToken;
}
