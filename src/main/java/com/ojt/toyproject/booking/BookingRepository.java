package com.ojt.toyproject.booking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Long> {

    @Query(value = "SELECT COUNT(*) FROM booking WHERE is_rented = 'N' AND isbn = :isbn")
    int getBookingCountByIsbn(@Param(value = "isbn") Long isbn);
}
