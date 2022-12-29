package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.category.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {

    //도서정보 CRUD
    void insertBookInfo(BookInfoDto bookInfoDto);
    List<BookInfoDto> getBookInfoList(SearchDto searchDto);
    BookInfoDto getBookInfoByIsbn(Long isbn);
    void updateBookInfo(BookInfoDto bookInfoDto);
    void deleteBookInfo(Long isbn);
    void deleteBookInfos(List<Long> isbnList);


    //소장도서 CRUD
    void insertBook(BookDto bookDto);
    List<BookDto> getBookList(SearchDto searchDto);
    void updateBook(BookDto bookDto);
    void deleteBook(Long seq);

    //카테고리 CRUD
    void insertCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategoryList();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long seq);

    //대여,반납시 상태 변경
    void addRentCount(Long bookSeq);
    void changeBookStatusToY(Long seq);
    void changeBookStatusToN(Long seq);
}
