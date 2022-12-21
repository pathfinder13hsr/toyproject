package com.ojt.toyproject.book;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper {
    void insertBookInfo(BookInfoDto bookInfoDto);
}
