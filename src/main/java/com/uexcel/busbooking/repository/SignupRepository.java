package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignupRepository extends JpaRepository<User,Long> {
    User findByEmail(String email);
}
