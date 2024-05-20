package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, Long> {
    @Query(nativeQuery = true,value = """
            SELECT *FROM booking\
             WHERE user_user_id=:userId AND route_id=:routeId AND ticket_status='valid'""")
    Booking findByUserId(Long userId,Long routeId);
}
