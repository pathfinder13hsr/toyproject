package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.BookService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        rentMapper.updateRent(seq); // rent 테이블 반납완료로 변경
        bookService.changeBookStatus(seq);
    }

    @Override
    public Map<String, List<RentDto>> getRentListById(String id) {
        Map<String, List<RentDto>> rentListMap = new HashMap<>();
        List<RentDto> rentList = rentMapper.getOnesRentList(id);
        List<RentDto> returnList = rentMapper.getOnesReturnList(id);
        rentListMap.put("대출중", rentList);
        rentListMap.put("반납완료", returnList);
        return rentListMap;
    }
}
