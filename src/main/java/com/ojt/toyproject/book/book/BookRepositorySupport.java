package com.ojt.toyproject.book.book;

import com.ojt.toyproject.SearchDto;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.criterion.Projection;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;
import java.util.List;

@Repository
@Slf4j
public class BookRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BookRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BookDto> getBookList(SearchDto searchDto) {
        QBookEntity qBookEntity = QBookEntity.bookEntity;
        if (searchDto == null){
            return jpaQueryFactory
                    .select(Projections.constructor(BookDto.class, qBookEntity))
                    .from(qBookEntity)
                    .fetch();
        } else {
            return jpaQueryFactory
                    .select(Projections.constructor(BookDto.class, qBookEntity))
                    .from(qBookEntity)
                    .where(isSearchable(searchDto.getSearchType(), searchDto.getKeyword()))
                    .fetch();
        }

    }


    public BooleanExpression isSearchable(String searchType, String keyword) {
        if (searchType != null && keyword != null) {
            if (searchType.equals("isbn")) {
                return QBookEntity.bookEntity.bookInfoEntity.isbn.eq(Long.valueOf(keyword));
            } else if(searchType.equals("seq")){
                return QBookEntity.bookEntity.seq.eq(Long.valueOf(keyword));
            } else if (searchType.equals("stockDate")) {
                return QBookEntity.bookEntity.stockDate.eq(LocalDate.parse(keyword));
            } else if (searchType.equals("isInStock")) {
                return QBookEntity.bookEntity.isInStock.eq(keyword);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void changeBookStatusToY (Long seq) {
        QBookEntity qBookEntity = QBookEntity.bookEntity;
        jpaQueryFactory
            .update(qBookEntity)
            .set(qBookEntity.isInStock, "Y")
            .where(QBookEntity.bookEntity.seq.eq(seq))
            .execute();
    }

    public void changeBookStatusToN (Long seq) {
        QBookEntity qBookEntity = QBookEntity.bookEntity;
        jpaQueryFactory
                .update(qBookEntity)
                .set(qBookEntity.isInStock, "N")
                .where(QBookEntity.bookEntity.seq.eq(seq))
                .execute();
    }









}
