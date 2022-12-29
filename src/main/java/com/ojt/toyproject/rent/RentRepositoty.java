package com.ojt.toyproject.rent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepositoty extends JpaRepository<RentEntity, Long> {

}
