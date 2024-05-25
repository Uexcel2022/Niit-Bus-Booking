package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BusRepository extends JpaRepository<Bus,String> {
    @Query(value = "SELECT p FROM Bus p WHERE p.busCode=:busCode")
    Bus findByBusCode(String busCode);
}
