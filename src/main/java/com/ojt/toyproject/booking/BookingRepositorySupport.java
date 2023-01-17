package com.ojt.toyproject.booking;

import com.ojt.toyproject.SearchDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class BookingRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public BookingRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    public List<BookingDto> getBookingList(SearchDto searchDto) {
        QBookingEntity qBookingEntity = QBookingEntity.bookingEntity;

        if (searchDto == null){
            return jpaQueryFactory
                    .select(Projections.constructor(BookingDto.class, qBookingEntity))
                    .from(qBookingEntity)
                    .fetch();
        } else {
            return jpaQueryFactory
                    .select(Projections.constructor(BookingDto.class, qBookingEntity))
                    .from(qBookingEntity)
                    .where(isSearchable(searchDto.getSearchType(), searchDto.getKeyword()))
                    .fetch();
        }
    }

    public BooleanExpression isSearchable(String searchType, String keyword) {
        if (searchType != null && keyword != null) {
            if (searchType.equals("isbn")) {
                return QBookingEntity.bookingEntity.bookInfoEntity.isbn.eq(Long.valueOf(keyword));
            } else if (searchType.equals("memberId")) {
                return QBookingEntity.bookingEntity.memberEntity.memberId.eq(keyword);
            } else if (searchType.equals("isRented")) {
                return QBookingEntity.bookingEntity.isRented.eq(keyword);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public void updateIsRented(Long seq) {
        QBookingEntity qBookingEntity = QBookingEntity.bookingEntity;
        jpaQueryFactory
                .update(qBookingEntity)
                .set(qBookingEntity.isRented, "Y")
                .where(qBookingEntity.seq.eq(seq))
                .execute();
    }

}
