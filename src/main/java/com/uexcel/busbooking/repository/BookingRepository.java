package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookingRepository extends CrudRepository<Booking, String> {
    @Query(nativeQuery = true,value = """
            SELECT *FROM booking\
             WHERE client_id=:client AND route_id=:routeId AND ticket_status='valid'""")
    Booking findByClientId(String client, String routeId);

    @Query(value = "SELECT p FROM Booking p WHERE p.ticketNumber=:ticketNumber")
    Booking findByTicketNumber(String ticketNumber);

    List<Booking> findByClientId(String clientId);

    @Query(value ="SELECT p FROM Booking p WHERE p.client.id=:clientId AND p.ticketStatus=:status")
    List<Booking> findByClientIdAndTicketStatus(String clientId, String status);
}
