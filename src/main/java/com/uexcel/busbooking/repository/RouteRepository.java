package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Route;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RouteRepository extends CrudRepository<Route, String> {
    Route findByRouteName(String routeName);
}
