package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Booking;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, Long> {
}
