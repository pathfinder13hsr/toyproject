package com.ojt.toyproject.rent;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Qualifier("Impl3_JPA")
public class RentServiceImpl3 implements RentService{
    @Override
    public void rentBook(RentDto rentDto) {

    }

    @Override
    public void returnBook(Long seq) {

    }

    @Override
    public Map<String, List<RentDto>> getRentListById(String id) {
//        마이바티스 코드
//        Map<String, List<RentDto>> rentListMap = new HashMap<>();
//        List<RentDto> rentList = rentMapper.getOnesRentList(id);
//        List<RentDto> returnList = rentMapper.getOnesReturnList(id);
//        rentListMap.put("대출중", rentList);
//        rentListMap.put("반납완료", returnList);
//        return rentListMap;




        return null;
    }
}
