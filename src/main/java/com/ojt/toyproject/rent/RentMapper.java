package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.BookDto;
import com.ojt.toyproject.book.BookInfoDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RentMapper {
    void insertRent(RentDto rentDto);

    void updateRent(Long seq);
}
