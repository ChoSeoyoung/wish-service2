package com.example.wishservice2.web.dto;

import com.example.wishservice2.domain.wish.Wish;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class WishListResponseDto {
    private Long id;
    private String title;
    private String period;
    private Integer cost;
    private LocalDateTime modifiedDate;

    public WishListResponseDto(Wish entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.period = entity.getPeriod();
        this.cost = entity.getCost();
        this.modifiedDate = entity.getModifiedDate();
    }
}
