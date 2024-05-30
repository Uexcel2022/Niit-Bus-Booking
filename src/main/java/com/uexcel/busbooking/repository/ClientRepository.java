package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client,String> {

    Client findByEmailAndStatus(String email, String status);

    Client findByEmailOrPhoneNumber(String email, String phone);

    Client findByPhoneNumberAndStatus(String phoneNumber, String status);

    Client findByIdAndStatus(String id, String status);

    List<Client> findAllByStatus(String status);
    Client findByEmail(String email);

}
