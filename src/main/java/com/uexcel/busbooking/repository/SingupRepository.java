package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SingupRepository extends JpaRepository<Signup,Long> {
}
