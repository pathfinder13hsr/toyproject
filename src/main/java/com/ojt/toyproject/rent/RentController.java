package com.ojt.toyproject.rent;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class RentController {

    //도서 대출
    @PostMapping("/rent")
    public void rentBook(RentDto rentDto){

    }

    //도서 반납
    @PutMapping("/return")
    public void returnBook(RentDto rentDto){

    }
}
