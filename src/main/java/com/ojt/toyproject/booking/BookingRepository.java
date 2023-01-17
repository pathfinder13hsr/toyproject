package com.ojt.toyproject.booking;

import com.ojt.toyproject.book.bookInfo.BookInfoEntity;
import com.ojt.toyproject.member.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM booking WHERE is_rented = 'N' AND isbn = :isbn", nativeQuery = true)
    int getBookingCountByIsbn(@Param(value = "isbn") Long isbn);

    boolean existsByBookInfoEntityAndMemberEntity (BookInfoEntity bookInfoEntity, MemberEntity memberEntity);

    @Query(value = "SELECT seq, isbn, member_id, booking_datetime, is_rented, row_number() over(order by booking_datetime) as rownum FROM booking WHERE is_rented = 'N' AND isbn= :isbn", nativeQuery = true)
    List<BookingEntity> getBookingRownum(@Param(value = "isbn") Long isbn);
}
