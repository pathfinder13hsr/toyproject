package com.ojt.toyproject.rent;

import java.util.List;
import java.util.Map;

public interface RentService {
    void rentBook(RentDto rentDto);
    void returnBook(Long seq);
    Map<String, List<RentDto>> getRentListById(String id);
}
