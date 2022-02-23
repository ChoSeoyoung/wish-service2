package com.example.wishservice2.web;

import com.example.wishservice2.domain.wish.Wish;
import com.example.wishservice2.service.WishService;
import com.example.wishservice2.web.dto.WishResponseDto;
import com.example.wishservice2.web.dto.WishSaveRequestDto;
import com.example.wishservice2.web.dto.WishUpdateRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@Slf4j
public class WishApiController {
    private final WishService wishService;

    @PostMapping("/api/v1/wishes")
    public Long save(@RequestBody WishSaveRequestDto requestDto){
        return wishService.save(requestDto);
    }

    @PutMapping("/api/v1/wishes/{id}")
    public Long update(@PathVariable Long id, @RequestBody WishUpdateRequestDto requestDto){
        return wishService.update(id, requestDto);
    }

    @GetMapping("/api/v1/wishes/{id}")
    public WishResponseDto findById(@PathVariable Long id){
        return wishService.findById(id);
    }

    @DeleteMapping("/api/v1/wishes/{id}")
    public Long delete(@PathVariable Long id){
        wishService.delete(id);
        return id;
    }

    @Data
    static class CheckBox {
        private List<String> checkBoxArr;
    }

    @PostMapping("/api/v1/wishes/delete")
    public @ResponseBody String deleteChecked(@RequestBody CheckBox data) throws ServletException, IOException{
        String result="0";
        List<String> checkBoxArr = data.getCheckBoxArr();
        for (String idx : checkBoxArr) {
            wishService.delete(Long.parseLong(idx));
        }

        return result;
    }

}
