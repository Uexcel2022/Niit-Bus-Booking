package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Checkin;
import org.springframework.data.repository.CrudRepository;

public interface CheckinRepository extends CrudRepository<Checkin, String> {
}
