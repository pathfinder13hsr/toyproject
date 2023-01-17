package com.ojt.toyproject.book.book;

import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@Entity(name = "book")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
//    private Long isbn;
    private int stockNum;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate stockDate;

    private String isInStock;

    @ManyToOne
    @JoinColumn(name = "isbn")
    private BookInfoEntity bookInfoEntity;
}
