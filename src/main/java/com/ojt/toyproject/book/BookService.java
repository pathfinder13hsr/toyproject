package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.category.CategoryDto;

import java.util.List;

public interface BookService {
    void insertBookInfo(BookInfoDto bookInfoDto);
    List<BookInfoDto> getBookInfoList(SearchDto searchDto);
    BookInfoDto getBookInfoByIsbn(Long isbn);
    void updateBookInfo(BookInfoDto bookInfoDto);
    int getRentCountByIsbn(Long isbn);
    int getInStockCountByIsbn(Long isbn);
    int getBookingCountByIsbn(Long isbn);
    void deleteBookInfo(Long isbn);
    void deleteBookInfo(List<Long> isbnList);

    void insertBook(BookDto bookDto);
    List<BookDto> getBookList(SearchDto searchDto);
    void updateBook(BookDto bookDto);
    void deleteBook(Long seq);


    void insertCategory(CategoryDto categoryDto);
    List<CategoryDto> getCategoryList();
    void updateCategory(CategoryDto categoryDto);
    void deleteCategory(Long seq);

    void addRentCount(Long bookSeq);
    void changeBookStatusToY(Long seq);
    void changeBookStatusToN(Long seq);


    public Long getIsbnBySeq(Long seq);

}
