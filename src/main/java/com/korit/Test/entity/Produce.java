package com.korit.Test.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Produce {
    private Integer produceId;
    private String name;
    private Integer price;
    private String color;
    private LocalDateTime createDt;
}
