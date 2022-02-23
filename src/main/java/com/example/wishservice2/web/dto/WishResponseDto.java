package com.example.wishservice2.web.dto;

import com.example.wishservice2.domain.wish.Wish;
import lombok.Getter;

@Getter
public class WishResponseDto {
    private Long id;
    private String title;
    private String period;
    private Integer cost;

    public WishResponseDto(Wish entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.period = entity.getPeriod();
        this.cost = entity.getCost();
    }
}
