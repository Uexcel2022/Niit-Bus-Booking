package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus,Long> {
}
