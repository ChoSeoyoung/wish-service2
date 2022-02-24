package com.example.wishservice2.web;

import com.example.wishservice2.domain.wish.RegionType;
import com.example.wishservice2.domain.wish.TravelType;
import com.example.wishservice2.domain.wish.Wish;
import com.example.wishservice2.service.WishService;
import com.example.wishservice2.web.dto.WishResponseDto;
import com.example.wishservice2.web.dto.WishSaveRequestDto;
import com.example.wishservice2.web.dto.WishUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final WishService wishService;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/basic/wishes")
    public String wishes(Model model){
        model.addAttribute("wishes",wishService.findAllDesc());
        return "basic/wishes";
    }

    @ModelAttribute("regionTypes")
    public RegionType[] regionTypes(){
        return RegionType.values();
    }

    @ModelAttribute("travelTypes")
    public List<TravelType> travelTypes(){
        List<TravelType> travelTypes = new ArrayList<>();
        travelTypes.add(new TravelType("ALONE","혼자"));
        travelTypes.add(new TravelType("FAMILY","가족"));
        travelTypes.add(new TravelType("COUPLE","커플"));
        travelTypes.add(new TravelType("FRIENDS","친구"));
        return travelTypes;
    }

    @GetMapping("/basic/wishes/add")
    public String save(){
        return "basic/addWish";
    }

    @GetMapping("/basic/wishes/update/{id}")
    public String update(@PathVariable Long id, Model model){
        WishResponseDto dto = wishService.findById(id);
        model.addAttribute("wish",dto);

        return "basic/editWish";
    }

}
