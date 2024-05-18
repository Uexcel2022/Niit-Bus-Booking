package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Signup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository extends JpaRepository<Signup,Long> {
    Signup findByEmail(String email);
}
