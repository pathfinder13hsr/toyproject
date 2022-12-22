package com.ojt.toyproject.book;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    //도서정보 CRUD
    void insertBookInfo(BookInfoDto bookInfoDto);
    List<BookInfoDto> getBookInfoList();
    void updateBookInfo(BookInfoDto bookInfoDto);
    void deleteBookInfo(Long isbn);

    //소장도서 CRUD
    void insertBook(BookDto bookDto);
    List<BookDto> getBookList();
    void updateBook(BookDto bookDto);
    void deleteBook(Long seq);

    //카테고리 CRUD
    void insertCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategoryList();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long seq);

    //대여,반납시 상태 변경
    void addRentCount(Long bookSeq);
    void changeBookStatus(Long seq);
}
