package com.example.wishservice2.service;

import com.example.wishservice2.domain.wish.Wish;
import com.example.wishservice2.domain.wish.WishRepository;
import com.example.wishservice2.web.dto.WishListResponseDto;
import com.example.wishservice2.web.dto.WishResponseDto;
import com.example.wishservice2.web.dto.WishSaveRequestDto;
import com.example.wishservice2.web.dto.WishUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WishService {
    private final WishRepository wishRepository;

    @Transactional
    public Long save(WishSaveRequestDto requestDto){
        return wishRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, WishUpdateRequestDto requestDto){
        Wish wish = wishRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시물이 없습니다. id="+id));

        wish.update(requestDto.getTitle(),requestDto.getPeriod(),requestDto.getCost(), requestDto.getRegionType(), requestDto.getTravelType());

        return id;
    }

    public WishResponseDto findById(Long id){
        Wish entity = wishRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시물이 없습니다. id="+id));

        return new WishResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<WishListResponseDto> findAllDesc(){
        return wishRepository.findAllDesc().stream()
                .map(WishListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Wish wish = wishRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        wishRepository.delete(wish);
    }
}
