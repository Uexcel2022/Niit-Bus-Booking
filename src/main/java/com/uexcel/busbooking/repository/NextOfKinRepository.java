package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.NextOfKin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NextOfKinRepository extends JpaRepository<NextOfKin, String> {
//    @Query(nativeQuery = true, value = "SELECT * FROM next_of_kin WHERE client_id=:clientId")
    NextOfKin findByClientIdAndStatus(String clientId, String status);
    NextOfKin findByIdAndStatus(String clientId, String status);

    NextOfKin findByClientId(String clientId);
}
