package com.example.wishservice2.web.dto;

import com.example.wishservice2.domain.wish.RegionType;
import com.example.wishservice2.domain.wish.TravelType;
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
    private RegionType regionType;
    private String travelType;

    @Builder
    public WishSaveRequestDto(String title, String period, Integer cost, RegionType regionType, String travelType) {
        this.title = title;
        this.period = period;
        this.cost = cost;
        this.regionType = regionType;
        this.travelType = travelType;
    }

    public Wish toEntity(){
        return Wish.builder()
                .title(title)
                .period(period)
                .cost(cost)
                .regionType(regionType)
                .travelType(travelType)
                .build();
    }
}
