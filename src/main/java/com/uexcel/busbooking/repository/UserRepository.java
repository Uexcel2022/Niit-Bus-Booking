package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);
    User findByEmailOrPhoneNumber(String email, String phone);

//    boolean existsByEmailIgnoreCase(String email);
}
