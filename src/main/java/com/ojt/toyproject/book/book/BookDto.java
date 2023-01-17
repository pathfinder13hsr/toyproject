package com.ojt.toyproject.book.book;

import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BookDto {
    private Long seq;
    private Long isbn;
    private int stockNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate;
    private String isInStock;

    @Builder(builderClassName = "byEntity", builderMethodName = "byEntity")
    public BookDto(BookEntity bookEntity){
        this.seq = bookEntity.getSeq();
        this.isbn = bookEntity.getBookInfoEntity().getIsbn();
        this.stockNum = bookEntity.getStockNum();
        this.stockDate = bookEntity.getStockDate();
        this.isInStock = bookEntity.getIsInStock();
    }

    public BookEntity byInsert(){
        return BookEntity
                .builder()
                .seq(this.seq)
                .bookInfoEntity(BookInfoEntity.builder().isbn(this.isbn).build())
                .stockNum(this.stockNum)
                .stockDate(this.stockDate)
                .isInStock("Y")
                .build();
    }

    public BookEntity byUpdate(BookEntity bookEntity){
        return BookEntity
                .builder()
                .seq(bookEntity.getSeq())
                .bookInfoEntity(BookInfoEntity.builder().isbn(this.isbn).build())
                .stockNum(this.stockNum)
                .stockDate(this.stockDate)
                .isInStock(this.isInStock)
                .build();
    }

}
