package com.example.wishservice2.web;

import com.example.wishservice2.domain.wish.Wish;
import com.example.wishservice2.domain.wish.WishRepository;
import com.example.wishservice2.web.dto.WishSaveRequestDto;
import com.example.wishservice2.web.dto.WishUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WishApiControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WishRepository wishRepository;

    @After
    public void tearDown() throws Exception{
        wishRepository.deleteAll();
    }

    @Test
    public void Wish가_등록된다() throws Exception{
        //given
        String title="모스크바 여행";
        String period="2028년";
        Integer cost=100;
        WishSaveRequestDto requestDto = WishSaveRequestDto.builder()
                .title(title)
                .period(period)
                .cost(cost).build();

        String url="http://localhost:"+port+"/api/v1/wishes";

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Wish> all = wishRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getPeriod()).isEqualTo(period);
        assertThat(all.get(0).getCost()).isEqualTo(cost);
    }

    @Test
    public void Wish가_수정된다() throws Exception{
        //given
        String title="모스크바 여행";
        String period="2028년";
        Integer cost=100;
        Wish savedWish=wishRepository.save(Wish.builder()
                .title(title)
                .period(period)
                .cost(cost)
                .build());

        Long updatedId=savedWish.getId();
        String updatedTitle="모스크바 여행2";
        String updatedPeriod="2029년";
        Integer updatedCost=200;
        WishUpdateRequestDto requestDto = WishUpdateRequestDto.builder()
                .title(updatedTitle)
                .period(updatedPeriod)
                .cost(updatedCost)
                .build();

        String url="http://localhost:"+port+"/basic/wishes/"+updatedId;

        HttpEntity<WishUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT,requestEntity,Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Wish> all = wishRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(updatedTitle);
        assertThat(all.get(0).getPeriod()).isEqualTo(updatedPeriod);
        assertThat(all.get(0).getCost()).isEqualTo(updatedCost);
    }
}