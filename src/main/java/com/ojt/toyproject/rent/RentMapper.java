package com.ojt.toyproject.rent;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RentMapper {
    void insertRent(RentDto rentDto);

    void updateRent(Long seq);
    List<RentDto> getOnesRentList(String id);
    List<RentDto> getOnesReturnList(String id);
}
