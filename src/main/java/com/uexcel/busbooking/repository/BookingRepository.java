package com.uexcel.busbooking.repository;

import com.uexcel.busbooking.entity.Booking;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookingRepository extends CrudRepository<Booking, String> {
    @Query(nativeQuery = true,value = """
            SELECT *FROM booking\
             WHERE client_id=:client AND route_id=:routeId AND ticket_status='valid'""")
    Booking findByUserId(String client,String routeId);

    @Query(value = "SELECT p FROM Booking p WHERE p.ticketNumber=:ticketNumber")
    Booking findByTicketNumber(String ticketNumber);
}
