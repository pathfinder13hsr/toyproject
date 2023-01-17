package com.ojt.toyproject.book;



import com.ojt.toyproject.Pagination;
import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.book.bookInfo.BookInfoDto;
import com.ojt.toyproject.book.category.CategoryDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@Qualifier("Impl1")
public class BookServiceImpl implements BookService{
    private final BookMapper bookMapper;

    public BookServiceImpl(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }

    //도서정보 CRUD
    @Override
    public void insertBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.insertBookInfo(bookInfoDto);
    }

    @Override
    public List<BookInfoDto> getBookInfoList(SearchDto searchDto) {
        if (searchDto.getPage() == null ){
            searchDto.setPage(1);
        }
        if (searchDto.getRecordSize() == null){
            searchDto.setRecordSize(10);
        }
        Pagination pagination = new Pagination(searchDto);
        searchDto.setPagination(pagination);
        List<BookInfoDto> bookInfoDtoList = bookMapper.getBookInfoList(searchDto);
        return bookInfoDtoList;
    }

    @Override
    public BookInfoDto getBookInfoByIsbn(Long isbn) {
        return null;
    }

    @Override
    public void updateBookInfo(BookInfoDto bookInfoDto) {
        bookMapper.updateBookInfo(bookInfoDto);
    }

    //isbn으로 현재 대출중인 책의 수량 조회
    @Override
    public int getRentCountByIsbn(Long isbn) {
        int rentCount = bookMapper.getBookInfoByIsbn(isbn).getRentCount();
        return rentCount;
    }

    @Override
    public int getInStockCountByIsbn(Long isbn) {
        int inStockCount = bookMapper.getBookInfoByIsbn(isbn).getInStockCount();
        return inStockCount;
    }

    @Override
    public int getBookingCountByIsbn(Long isbn) {
        int bookingCount = bookMapper.getBookInfoByIsbn(isbn).getBookingCount();
        return bookingCount;
    }

    @Override
    public void deleteBookInfo(Long isbn) {
        if (getRentCountByIsbn(isbn) == 0){
            bookMapper.deleteBookInfo(isbn);
        }else {
            log.info("삭제 불가");
        }
    }

    @Override
    public void deleteBookInfo(List<Long> isbnList) {
        for (int i=0; i<isbnList.size(); i++){
            if (getRentCountByIsbn(isbnList.get(i))!=0){
                isbnList.remove(i);
            }
        }
        if (isbnList.size()!=0){
            bookMapper.deleteBookInfos(isbnList);
        }else {
            log.info("삭제 가능한 도서가 없음");
        }

    }




    //소장도서 CRUD
    @Override
    public void insertBook(BookDto bookDto) {
        bookMapper.insertBook(bookDto);
    }

    @Override
    public List<BookDto> getBookList(SearchDto searchDto) {
        List<BookDto> bookDtoList = bookMapper.getBookList(searchDto);
        return bookDtoList;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        bookMapper.updateBook(bookDto);
    }

    @Override
    public void deleteBook(Long seq) {
        bookMapper.deleteBook(seq);
    }



    //카테고리 CRUD
    @Override
    public void insertCategory(CategoryDto categoryDto) {
        bookMapper.insertCategory(categoryDto);
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        List<CategoryDto> categoryDtoList = bookMapper.getCategoryList();
        return categoryDtoList;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        bookMapper.updateCategory(categoryDto);
    }

    @Override
    public void deleteCategory(Long seq) {
        bookMapper.deleteCategory(seq);
    }

    @Override
    public void addRentCount(Long bookSeq) {
        bookMapper.addRentCount(bookSeq);
    }

    @Override
    public void changeBookStatusToY(Long seq) {
        bookMapper.changeBookStatusToY(seq);
    }
    @Override
    public void changeBookStatusToN(Long seq) {
        bookMapper.changeBookStatusToN(seq);
    }

    @Override
    public Long getIsbnBySeq(Long seq) {
        return null;
    }
}
