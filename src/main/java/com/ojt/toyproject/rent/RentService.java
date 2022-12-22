package com.ojt.toyproject.rent;

public interface RentService {
    void rentBook(RentDto rentDto);
    void returnBook(Long seq);
}
