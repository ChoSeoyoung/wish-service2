package com.example.wishservice2.web.dto;

import com.example.wishservice2.domain.wish.RegionType;
import com.example.wishservice2.domain.wish.TravelType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class WishUpdateRequestDto {
    private String title;
    private String period;
    private Integer cost;
    private RegionType regionType;
    private String travelType;

    @Builder
    public WishUpdateRequestDto(String title, String period, Integer cost, RegionType regionType, String travelType) {
        this.title = title;
        this.period = period;
        this.cost = cost;
        this.regionType = regionType;
        this.travelType = travelType;
    }
}
