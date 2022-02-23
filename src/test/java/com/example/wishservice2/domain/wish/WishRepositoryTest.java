package com.example.wishservice2.domain.wish;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WishRepositoryTest {
    @Autowired
    WishRepository wishRepository;

    @After
    public void cleanup(){
        wishRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title="테스트 게시글";
        String period="테스트 기간";
        Integer cost=100;

        wishRepository.save(Wish.builder()
                        .title(title)
                        .period(period)
                        .cost(cost)
                .build());

        //when
        List<Wish> wishList = wishRepository.findAll();

        //then
        Wish wish = wishList.get(0);
        assertThat(wish.getTitle()).isEqualTo(title);
        assertThat(wish.getPeriod()).isEqualTo(period);
        assertThat(wish.getCost()).isEqualTo(cost);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019, 6, 4, 0, 0, 0);
        wishRepository.save(Wish.builder()
                .title("title")
                .period("period")
                .cost(100)
                .build());
        //when
        List<Wish> wishList = wishRepository.findAll();

        //then
        Wish wish = wishList.get(0);

        System.out.println(">>>>>>>>> createDate=" + wish.getCreatedDate() + ", modifiedDate=" + wish.getModifiedDate());

        assertThat(wish.getCreatedDate()).isAfter(now);
        assertThat(wish.getModifiedDate()).isAfter(now);
    }
}