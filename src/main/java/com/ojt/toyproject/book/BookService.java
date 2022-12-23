package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.member.MemberDto;

import java.util.List;

public interface BookService {
    void insertBookInfo(BookInfoDto bookInfoDto);
    List<BookInfoDto> getBookInfoList(SearchDto searchDto);
    void updateBookInfo(BookInfoDto bookInfoDto);
    void deleteBookInfo(Long isbn);
    void deleteBookInfo(List<Long> isbnList);


    void insertBook(BookDto bookDto);
    List<BookDto> getBookList();
    void updateBook(BookDto bookDto);
    void deleteBook(Long seq);


    void insertCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategoryList();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long seq);

    void addRentCount(Long bookSeq);
    void changeBookStatus(Long seq);

}
