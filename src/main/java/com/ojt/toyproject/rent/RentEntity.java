package com.ojt.toyproject.rent;

import com.ojt.toyproject.book.book.BookEntity;
import com.ojt.toyproject.member.MemberEntity;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@ToString
@Entity(name = "rent")
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
//    private Long bookSeq;
//    private String memberId;
    private LocalDate rentDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private String isReturned;


    @ManyToOne
    @JoinColumn(name = "bookSeq")
    private BookEntity bookEntity;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private MemberEntity memberEntity;


}
