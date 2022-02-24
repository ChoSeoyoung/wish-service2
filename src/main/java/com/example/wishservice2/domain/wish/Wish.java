package com.example.wishservice2.domain.wish;

import com.example.wishservice2.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 자동 추가
@Entity
public class Wish extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length=500, nullable=false)
    private String title;

    @Column(nullable=false)
    private String period;

    @Column(length=500, nullable=false)
    private Integer cost;

    @Column(nullable = false)
    private RegionType regionType; //여행 지역

    @Column(nullable = false)
    private String travelType; //여행 타입
    //private Boolean open; //공개 여부

    @Builder
    public Wish(String title, String period, Integer cost, RegionType regionType, String travelType) {
        this.title = title;
        this.period = period;
        this.cost = cost;
        this.regionType = regionType;
        this.travelType = travelType;
    }

    public void update(String title, String period, Integer cost, RegionType regionType, String travelType){
        this.title = title;
        this.period = period;
        this.cost = cost;
        this.regionType = regionType;
        this.travelType = travelType;
    }
}
