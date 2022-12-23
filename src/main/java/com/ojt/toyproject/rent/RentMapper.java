package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.BookDto;
import com.ojt.toyproject.book.BookInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RentMapper {
    void insertRent(RentDto rentDto);

    void updateRent(Long seq);
    List<RentDto> getOnesRentList(String id);
    List<RentDto> getOnesReturnList(String id);
}
