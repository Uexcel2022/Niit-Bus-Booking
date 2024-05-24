package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Route;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RouteRepository extends CrudRepository<Route, Long> {
    Route findByRouteName(String routeName);
}
