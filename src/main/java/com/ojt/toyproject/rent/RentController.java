package com.ojt.toyproject.rent;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    //도서 대출
    @PostMapping("/rent")
    public void rentBook(@RequestBody RentDto rentDto){
        rentService.rentBook(rentDto);
    }

    //도서 반납
    @PutMapping("/return/{seq}")
    public void returnBook(@PathVariable Long seq){
        rentService.returnBook(seq);
    }
}
