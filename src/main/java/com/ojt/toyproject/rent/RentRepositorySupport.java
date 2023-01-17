package com.ojt.toyproject.rent;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class RentRepositorySupport {
    private final JPAQueryFactory jpaQueryFactory;

    public RentRepositorySupport(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

// rent 테이블 return_date 추가, is_returned Y로 변경
    public void updateRent(Long seq) {
        QRentEntity qRentEntity = QRentEntity.rentEntity;
        jpaQueryFactory
                .update(qRentEntity)
                .set(qRentEntity.returnDate, LocalDate.now())
                .set(qRentEntity.isReturned, "Y")
                .where(qRentEntity.seq.eq(seq))
                .execute();
    }


    public List<RentDto> getOnesRentList(String id) {
        QRentEntity qRentEntity = QRentEntity.rentEntity;

        return jpaQueryFactory
                .select(Projections.constructor(RentDto.class, qRentEntity))
                .from(qRentEntity)
                .where(qRentEntity.memberEntity.memberId.eq(id), qRentEntity.isReturned.eq("N"))
                .fetch();
    }

    public List<RentDto> getOnesReturnList(String id) {
        QRentEntity qRentEntity = QRentEntity.rentEntity;

        return jpaQueryFactory
                .select(Projections.constructor(RentDto.class, qRentEntity))
                .from(qRentEntity)
                .where(qRentEntity.memberEntity.memberId.eq(id), qRentEntity.isReturned.eq("Y"))
                .fetch();
    }
}
