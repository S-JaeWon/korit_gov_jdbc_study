package com.korit.study2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserListRespDto {
    private Integer userId;
    private String username;
    private String email;
    private LocalDateTime createDt;
}
