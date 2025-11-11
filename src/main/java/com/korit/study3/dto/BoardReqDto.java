package com.korit.study3.dto;

import com.korit.study3.entity.Board;
import lombok.Data;


@Data
public class BoardReqDto {
    private String title;
    private String content;
    private String username;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .username(username)
                .build();
    }

}
