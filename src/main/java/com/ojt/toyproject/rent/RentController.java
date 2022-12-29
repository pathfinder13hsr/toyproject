package com.ojt.toyproject.rent;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RentController {
    private final RentService rentService;
    private final RentService rentService3;

    public RentController(@Qualifier("Impl1") RentService rentService, @Qualifier("Impl3_JPA") RentService rentService3) {
        this.rentService = rentService;
        this.rentService3 = rentService3;
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
