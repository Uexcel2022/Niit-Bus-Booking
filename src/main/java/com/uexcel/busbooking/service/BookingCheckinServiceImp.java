package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.CheckinDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.*;
import com.uexcel.busbooking.repository.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingCheckinServiceImp implements BookingCheckinService {
    private final BusRouteService busRouteService;
    private final BookingRepository bookingRepository;
    private final UserService userService;
    private final CheckinRepository checkinRepository;
    private  final UserWalletService userWalletService;
    public BookingCheckinServiceImp(BusRouteService busRouteService,
                                    BookingRepository bookingRepository, UserService userService,
                                    CheckinRepository checkinRepository, UserWalletService userWalletService
    ) {
        this.busRouteService = busRouteService;
        this.bookingRepository = bookingRepository;
        this.userService = userService;
        this.checkinRepository = checkinRepository;
        this.userWalletService = userWalletService;
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
        Optional<User> user = userService.getUserRepository().findById(userId);
        User u;
        if(user.isPresent()){
            u = user.get();
        } else throw new RuntimeException("User not found");

        UserWallet wallet = userWalletService.getUserWalletRepository()
                .findUserWalletByUserId(userId);
        walletBalance = wallet.getBalance();
        Optional<Route> route = busRouteService.getRouteRepository().findById(routeId);

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
        booking.setRoute(r);
        booking.setUser(u);


        booking.setTicketStatus("valid");

        bookingInfoDto.setFName(u.getFirstName());
        bookingInfoDto.setLName(u.getLastName());
        bookingInfoDto.setTickNumber(booking.getTicketNumber());
        bookingInfoDto.setRouteName(r.getRouteName());
        bookingInfoDto.setAmount(r.getPrice());
        bookingInfoDto.setBookingDate(booking.getBookingDate());
        bookingInfoDto.setTickStatus(booking.getTicketStatus());
        bookingRepository.save(booking);
        userWalletService.getUserWalletRepository().save(wallet);
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

        Bus bus = busRouteService.getBusRepository().findByBusCode(checkinDto.getBusCode());

        if(bus == null) {
            throw new RuntimeException("Invalid bus code");
        }

        if(!bus.getRoute().getId().equals(booking.getRoute().getId())){
            throw new RuntimeException("The ticket is not for this route");
        }
//        checkin.setBus(bus);
        booking.setTicketStatus("used");
        checkin.setBooking(booking);
        bookingRepository.save(booking);
        checkinRepository.save(checkin);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Checkin successful");
        return responseDto;
    }
}
