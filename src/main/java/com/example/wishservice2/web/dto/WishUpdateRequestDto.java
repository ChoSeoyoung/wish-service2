package com.example.wishservice2.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishUpdateRequestDto {
    private String title;
    private String period;
    private Integer cost;

    @Builder
    public WishUpdateRequestDto(String title, String period, Integer cost) {
        this.title = title;
        this.period = period;
        this.cost = cost;
    }
}
