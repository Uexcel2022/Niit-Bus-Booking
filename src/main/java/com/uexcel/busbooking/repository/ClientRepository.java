package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {
    Client findByEmail(String email);
    Client findByEmailOrPhoneNumber(String email, String phone);

    Client findByPhoneNumber(String phoneNumber);

//    boolean existsByEmailIgnoreCase(String email);
}
