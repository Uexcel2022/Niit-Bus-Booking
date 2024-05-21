package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.NextOfKin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NextOfKinRepository extends JpaRepository<NextOfKin, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM next_of_kin WHERE user_id=:userId")
    NextOfKin findByUserId(Long userId);
}
