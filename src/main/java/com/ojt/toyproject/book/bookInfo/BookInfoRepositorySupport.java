package com.ojt.toyproject.book.bookInfo;


import com.ojt.toyproject.book.book.QBookEntity;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

@Repository
public class BookInfoRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BookInfoRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public void addRentCount(Long bookSeq) {
        QBookEntity qBookEntity = QBookEntity.bookEntity;
        QBookInfoEntity qBookInfoEntity = QBookInfoEntity.bookInfoEntity;

        jpaQueryFactory
                .update(qBookInfoEntity)
                .set(qBookInfoEntity.totalRentCount, qBookInfoEntity.totalRentCount.add(1))
                .where(qBookInfoEntity.isbn.eq(
                        jpaQueryFactory.select(qBookEntity.bookInfoEntity.isbn).from(qBookEntity).where(qBookEntity.seq.eq(bookSeq))
                ))
                .execute();
    }
}
