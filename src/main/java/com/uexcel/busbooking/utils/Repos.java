package com.uexcel.busbooking.utils;

import com.uexcel.busbooking.repository.*;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Repos {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CheckinRepository checkinRepository;
    @Autowired
    private WallTransactionRepository wallTransactionRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private NextOfKinRepository nextOfKinRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private ClientWalletRepository clientWalletRepository;

}
