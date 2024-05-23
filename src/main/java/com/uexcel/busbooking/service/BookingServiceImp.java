package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.CheckinDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.*;
import com.uexcel.busbooking.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final CheckinRepository checkinRepository;
    private  final UserWalletRepository walletRepository;
    public BookingServiceImp(BusRepository busRepository,
                             RouteRepository routeRepository,
                             BookingRepository bookingRepository,
                             UserRepository userRepository,
                             CheckinRepository checkinRepository,
                             UserWalletRepository walletRepository
                             ) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.checkinRepository = checkinRepository;
        this.walletRepository = walletRepository;
    }
    @Override
    public Bus addBus(BusRouteDto busRouteDto) {
        Bus bus = new Bus();
        Route route = new Route();
        bus.setBusCode(busRouteDto.getBusCode());
        bus.setBrand(busRouteDto.getBrand());
        bus.setModel(busRouteDto.getModel());
        bus.setBusCapacity(busRouteDto.getBusCapacity());
        bus.setServiceStartDate(busRouteDto.getServiceStartDate());
        route.setRouteName(busRouteDto.getRouteName());
        route.setPrice(busRouteDto.getPrice());
        bus.setRoute(route);
        routeRepository.save(route);
        return busRepository.save(bus);
    }

    @Override
    public BookingInfoDto processBooking(Long userId, Long routeId) {

        Booking bk = bookingRepository.findByUserId(userId,routeId);
        if(bk != null){

            throw new RuntimeException("You have unused ticked on this route!!!"+" Ticked Number: "
                    +bk.getTicketNumber());
        }
        double price;
        double walletBalance;
        double newWalletBalance;
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        Booking booking = new Booking();
        Optional<User> user = userRepository.findById(userId);
        User u;
        if(user.isPresent()){
            u = user.get();
        } else throw new RuntimeException("User not found");
        booking.setUser(u);

        UserWallet wallet = walletRepository.findUserWalletByUserId(userId);
        walletBalance = wallet.getBalance();
        Optional<Route> route = routeRepository.findById(routeId);
        Route r;
        if(route.isPresent()){
            r = route.get();
        } else throw new RuntimeException("Route not found");

        price = r.getPrice();

        if(walletBalance < price){
            throw new RuntimeException("Insufficient balance");
        }

        newWalletBalance = walletBalance - price;
        wallet.setBalance(newWalletBalance);


        booking.setTicketStatus("valid");

        bookingInfoDto.setFName(u.getFirstName());
        bookingInfoDto.setLName(u.getLastName());
        bookingInfoDto.setTickNumber(booking.getTicketNumber());
        bookingInfoDto.setRouteName(r.getRouteName());
        bookingInfoDto.setAmount(r.getPrice());
        bookingInfoDto.setBookingDate(booking.getBookingDate());
        bookingInfoDto.setTickStatus(booking.getTicketStatus());
        walletRepository.save(wallet);
        bookingRepository.save(booking);
        return bookingInfoDto;

    }

    @Override
    public ResponseDto processCheckin(CheckinDto checkinDto) {
        Checkin checkin = new Checkin();
        Booking booking = bookingRepository.findByTicketNumber(checkinDto.getTicketNumber());

        if(booking == null){
            throw new RuntimeException("Invalid ticket number");
        }
        switch (booking.getTicketStatus()) {
            case "used" -> throw new RuntimeException("Ticket already used");
            case "expired" -> throw new RuntimeException("Ticket already expired");
            case "refund" -> throw new RuntimeException("You been refunded on this ticked");
        }

        Bus bus = busRepository.findByBusCode(checkinDto.getBusCode());

        if(bus == null) {
            throw new RuntimeException("Invalid bus code");
        }

        if(!bus.getRoute().getId().equals(booking.getRoute().getId())){
            throw new RuntimeException("The ticket is not for this route");
        }
        checkin.setBus(bus);
        booking.setTicketStatus("used");
        checkin.setBooking(booking);
        bookingRepository.save(booking);
        checkinRepository.save(checkin);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Checkin successful");
        return responseDto;
    }
}
