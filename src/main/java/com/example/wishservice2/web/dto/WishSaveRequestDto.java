package com.example.wishservice2.web.dto;

import com.example.wishservice2.domain.wish.Wish;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishSaveRequestDto {
    private String title;
    private String period;
    private Integer cost;

    @Builder
    public WishSaveRequestDto(String title, String period, Integer cost) {
        this.title = title;
        this.period = period;
        this.cost = cost;
    }

    public Wish toEntity(){
        return Wish.builder()
                .title(title)
                .period(period)
                .cost(cost)
                .build();
    }
}
