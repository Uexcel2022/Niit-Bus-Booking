package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Checkin;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CheckinRepository extends CrudRepository<Checkin, String> {

    //The buses that travelled the route
    List<Checkin> findByBusCurrentRouteId(String busCurrentRouteId);

    //The routes the bus has travelled
    List<Checkin> findByBusCode(String busCode);

    //The buses that travelled the route as at date
    List <Checkin> findByBusCurrentRouteIdAndCheckinDate(String busCurrentRouteId, LocalDate date);

    //The routes the buss traveled as at date
    List<Checkin> findByBusCodeAndCheckinDate (String busCode, LocalDate date);
}
