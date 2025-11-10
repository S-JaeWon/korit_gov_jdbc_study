package com.korit.study1.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {
    private Integer userId;
    private String username;
    private String password;
    private Integer age;
    private LocalDateTime createDt;
}
