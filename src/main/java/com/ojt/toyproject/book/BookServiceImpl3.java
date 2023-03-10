package com.ojt.toyproject.book;

import com.ojt.toyproject.SearchDto;
import com.ojt.toyproject.book.book.BookDto;
import com.ojt.toyproject.book.book.BookEntity;
import com.ojt.toyproject.book.book.BookRepository;
import com.ojt.toyproject.book.book.BookRepositorySupport;
import com.ojt.toyproject.book.bookInfo.*;
import com.ojt.toyproject.book.category.CategoryDto;
import com.ojt.toyproject.book.category.CategoryEntity;
import com.ojt.toyproject.book.category.CategoryRepository;
import com.ojt.toyproject.booking.BookingRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("Impl3_JPA")
@Slf4j
public class BookServiceImpl3 implements BookService {
    private final BookInfoRepository bookInfoRepository;
    private final BookInfoRepositorySupport bookInfoRepositorySupport;
    private final BookingRepository bookingRepository;
    private final BookRepository bookRepository;
    private final BookRepositorySupport bookRepositorySupport; //queryDsl 구현
    private final CategoryRepository categoryRepository;

    public BookServiceImpl3(BookInfoRepository bookInfoRepository, BookInfoRepositorySupport bookInfoRepositorySupport, BookingRepository bookingRepository, BookRepository bookRepository, BookRepositorySupport bookRepositorySupport, CategoryRepository categoryRepository) {
        this.bookInfoRepository = bookInfoRepository;
        this.bookInfoRepositorySupport = bookInfoRepositorySupport;
        this.bookingRepository = bookingRepository;
        this.bookRepository = bookRepository;
        this.bookRepositorySupport = bookRepositorySupport;
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void insertBookInfo(BookInfoDto bookInfoDto) {
        if (!bookInfoRepository.existsByIsbn(bookInfoDto.getIsbn())) {
            bookInfoRepository.save(bookInfoDto.byInsert());
        }
    }

    @Override
    public List<BookInfoDto> getBookInfoList(SearchDto searchDto) {
        Page<BookInfoEntity> bookInfoEntityList;
        Pageable pageable = PageRequest.of(0, 10);
        Specification<BookInfoEntity> spec = (root, query, criteriaBuilder) -> null;

        if (searchDto != null && searchDto.getPage() != null && searchDto.getRecordSize() != null) {
            int page = searchDto.getPage();
            int pageSize = searchDto.getRecordSize();
            pageable = PageRequest.of(page, pageSize);
        }

        if (searchDto != null && searchDto.getSearchType() != null && searchDto.getKeyword() != null) {
            String searchType = searchDto.getSearchType();
            String keyword = searchDto.getKeyword();
            if (searchType.equals("isbn")) {
                spec = BookInfoSpec.equalIsbn(Long.valueOf(keyword));
            } else if (searchType.equals("category")) {
                spec = BookInfoSpec.equalCategory((Integer.valueOf(keyword)));
            } else if (searchType.equals("title")) {
                spec = BookInfoSpec.likeTitle(keyword);
            } else if (searchType.equals("author")) {
                spec = BookInfoSpec.likeAuthor(keyword);
            } else if (searchType.equals("publisher")) {
                spec = BookInfoSpec.likePublisher(keyword);
            } else {
                log.info("검색조건 오류");
            }
        }
        bookInfoEntityList = bookInfoRepository.findAll(spec, pageable);

        List<BookInfoDto> bookInfoDtoList = new ArrayList<>();
        for (BookInfoEntity bookInfoEntity : bookInfoEntityList) {
            Long isbn = bookInfoEntity.getIsbn();
            BookInfoDto bookInfoDto = BookInfoDto.byEntity().bookInfoEntity(bookInfoEntity).build();
            bookInfoDto.setTotalStockCount(bookRepository.countByBookInfoEntity(bookInfoEntity));
            bookInfoDto.setRentCount(getRentCountByIsbn(isbn));
            bookInfoDto.setInStockCount(getInStockCountByIsbn(isbn));
            bookInfoDto.setBookingCount(getBookingCountByIsbn(isbn));
            bookInfoDtoList.add(bookInfoDto);
        }
        return bookInfoDtoList;

    }

    @Override
    public BookInfoDto getBookInfoByIsbn(Long isbn) {
        BookInfoDto bookInfoDto = BookInfoDto.byEntity().bookInfoEntity(bookInfoRepository.findByIsbn(isbn)).build();
        return bookInfoDto;
    }

    @Override
    public void updateBookInfo(BookInfoDto bookInfoDto) {
        BookInfoEntity bookInfoEntity = bookInfoRepository.findByIsbn(bookInfoDto.getIsbn());
        bookInfoRepository.save(bookInfoDto.byUpdate(bookInfoEntity));
    }

    @Override
    public int getRentCountByIsbn(Long isbn) {
        return bookRepository.getRentCountByIsbn(isbn);
    }

    @Override
    public int getInStockCountByIsbn(Long isbn) {
        return bookRepository.getInStockCountByIsbn(isbn);
    }

    @Override
    public int getBookingCountByIsbn(Long isbn) {
        return bookingRepository.getBookingCountByIsbn(isbn);
    }



    @Override
    public void deleteBookInfo(Long isbn) {
        if (getRentCountByIsbn(isbn) == 0 && bookInfoRepository.existsByIsbn(isbn)){
            bookInfoRepository.deleteById(isbn);
        } else {
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
            bookInfoRepository.deleteAllByIdInBatch(isbnList);
        }else {
            log.info("삭제 가능한 도서가 없음");
        }

    }

    @Override
    public void insertBook(BookDto bookDto) {
        Long isbn = bookDto.getIsbn();
        BookInfoEntity bookInfoEntity = bookInfoRepository.findByIsbn(isbn);
        if(bookRepository.existsByBookInfoEntity(bookInfoEntity)){
            bookDto.setStockNum(bookRepository.getMaxStockNumByIsbn(isbn)+1);
        } else {
            bookDto.setStockNum(1);
        }
        if (bookDto.getStockDate() == null) {
            bookDto.setStockDate(LocalDate.now());
        }
        bookRepository.save(bookDto.byInsert());
    }

    @Override
    public List<BookDto> getBookList(SearchDto searchDto) {
        List<BookDto> bookDtoList = bookRepositorySupport.getBookList(searchDto);
        return bookDtoList;
    }

    @Override
    public void updateBook(BookDto bookDto) {
        BookEntity bookEntity = bookRepository.findBySeq(bookDto.getSeq());
        bookRepository.save(bookDto.byUpdate(bookEntity));
    }

    @Override
    public void deleteBook(Long seq) {
        bookRepository.deleteById(seq);
    }

    @Override
    public void insertCategory(CategoryDto categoryDto) {
        categoryRepository.save(categoryDto.byInsert());
    }

    @Override
    public List<CategoryDto> getCategoryList() {
        List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntityList) {
            CategoryDto categoryDto = CategoryDto.byEntity().categotyEntity(categoryEntity).build();
            categoryDtoList.add(categoryDto);
        }
        return categoryDtoList;
    }

    @Override
    public void updateCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = categoryRepository.findBySeq(categoryDto.getSeq());
        categoryRepository.save(categoryDto.byUpdate(categoryEntity));
    }

    @Override
    public void deleteCategory(Long seq) {
        categoryRepository.deleteById(seq);
    }


    @Override
    public void addRentCount(Long bookSeq) {
        bookInfoRepositorySupport.addRentCount(bookSeq);
    }

    @Override
    public void changeBookStatusToY(Long seq) {
        bookRepositorySupport.changeBookStatusToY(seq);
    }

    @Override
    public void changeBookStatusToN(Long seq) {
        bookRepositorySupport.changeBookStatusToN(seq);
    }



    //스케줄러 테스트용
    @Override
    public Long getIsbnBySeq(Long seq) {
        return bookRepository.getIsbnBySeq(seq);
    }
}
