package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.BookService;
import org.springframework.stereotype.Service;

@Service
public class RentServiceImpl implements RentService{
    private final RentMapper rentMapper;
    private final BookService bookService; //BookMapper 또는 BookService 주입, 서비스 불러오는게 더 적절
    public RentServiceImpl(RentMapper rentMapper, BookService bookService) {
        this.rentMapper = rentMapper;
        this.bookService = bookService;
    }

    @Override
    public void rentBook(RentDto rentDto) {
        rentMapper.insertRent(rentDto);
        bookService.addRentCount(rentDto.getBookSeq());
        bookService.changeBookStatus(rentDto.getSeq());
    }

    @Override
    public void returnBook(Long seq) {
        rentMapper.updateRent(seq);
        bookService.changeBookStatus(seq);
    }
}
