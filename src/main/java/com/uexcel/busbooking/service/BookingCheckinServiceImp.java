package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.BusCheckinQueryDto;
import com.uexcel.busbooking.dto.CheckinDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.*;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingCheckinServiceImp implements BookingCheckinService {
    private final BusRouteService busRouteService;
    private final BookingRepository bookingRepository;
    private final ClientService clientService;
    private final CheckinRepository checkinRepository;
    private  final ClientWalletService clientWalletService;
    public BookingCheckinServiceImp(BusRouteService busRouteService,
                                    BookingRepository bookingRepository, ClientService clientService,
                                    CheckinRepository checkinRepository, ClientWalletService clientWalletService
    ) {
        this.busRouteService = busRouteService;
        this.bookingRepository = bookingRepository;
        this.clientService = clientService;
        this.checkinRepository = checkinRepository;
        this.clientWalletService = clientWalletService;
    }


    @Override
    public BookingInfoDto processBooking(String userId, String routeId) {

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
        Optional<Client> user = clientService.getUserRepository().findById(userId);
        Client u;
        if(user.isPresent()){
            u = user.get();
        } else throw new RuntimeException("User not found");

        ClientWallet wallet = clientWalletService.getUserWalletRepository()
                .findUserWalletByUserId(userId);
        walletBalance = wallet.getBalance();
        Optional<Route> route = busRouteService.getRouteRepository().findById(routeId);

        Route r;
        if(route.isPresent()){
            r = route.get();
        } else throw new RuntimeException("Route not found");

        price = r.getPrice();

        if(walletBalance < price){
            throw new CustomException("Insufficient balance","402");
        }

        newWalletBalance = walletBalance - price;
        wallet.setBalance(newWalletBalance);
        booking.setRoute(r);
        booking.setClient(u);


        booking.setTicketStatus("valid");

        bookingInfoDto.setFName(u.getFirstName());
        bookingInfoDto.setLName(u.getLastName());
        bookingInfoDto.setTickNumber(booking.getTicketNumber());
        bookingInfoDto.setRouteName(r.getRouteName());
        bookingInfoDto.setAmount(r.getPrice());
        bookingInfoDto.setBookingDate(booking.getBookingDate());
        bookingInfoDto.setTickStatus(booking.getTicketStatus());
        bookingRepository.save(booking);
        clientWalletService.getUserWalletRepository().save(wallet);
        return bookingInfoDto;

    }

    @Override
    public ResponseDto processCheckin(CheckinDto checkinDto) {
        Checkin checkin = new Checkin();
        Booking booking = bookingRepository.findByTicketNumber(checkinDto.getTicketNumber());

        if(booking == null){
            throw new CustomException("Ticket not found.","404");
        }
        switch (booking.getTicketStatus()) {
            case "used" -> throw new CustomException("Ticket already used.","400");
            case "expired" -> throw new CustomException("Ticket already expired.","400");
            case "refund" -> throw new CustomException("You been refunded on this ticked.","400");
        }

        Bus bus = busRouteService.getBusRepository().findByBusCode(checkinDto.getBusCode());

        if(bus == null) {
            throw new CustomException("Bus not found.","404");
        }

        if(!bus.getRoute().getId().equals(booking.getRoute().getId())){
            throw new CustomException("The ticket is not for this route.");
        }

        checkin.setBusCode(bus.getBusCode());
        checkin.setBusCurrentRouteId(bus.getRoute().getId());
        booking.setTicketStatus("used");
        checkin.setBooking(booking);
        bookingRepository.save(booking);
        checkinRepository.save(checkin);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Checkin successful.");
        return responseDto;
    }

    public List<Checkin> findBusesOnRoute(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = checkinRepository.findByBusCurrentRouteId(
                busCheckinQueryDto.getBusCurrentRouteId());
        if(checkin == null){
            throw new CustomException("Route not found.","404");
        }
        return checkin;
    }

    public List<Checkin> findBusesOnRouteByDate(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = checkinRepository.findByBusCurrentRouteIdAndCheckinDate(
                busCheckinQueryDto.getBusCurrentRouteId(), busCheckinQueryDto.getDate());
        if(checkin == null){
            throw new CustomException("Not found.","404");
        }
        return checkin;
    }

    public List<Checkin> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = checkinRepository.findByBusCode(busCheckinQueryDto.getBusCode());
        if(checkin == null){
            throw new CustomException("Not found.","404");
        }
        return checkin;
    }

    @Override
    public List<Checkin> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto) {
        List<Checkin> checkin = checkinRepository.findByBusCodeAndCheckinDate(
                 busCheckinQueryDto.getBusCode(),busCheckinQueryDto.getDate());
        if(checkin == null){
            throw new CustomException("Not found.","404");
        }
        return checkin;
    }


}





//OLD CODE
//    @Override
//    public ResponseDto processCheckin(CheckinDto checkinDto) {
//        Checkin checkin = new Checkin();
//        Booking booking = bookingRepository.findByTicketNumber(checkinDto.getTicketNumber());
//
//        if(booking == null){
//            throw new RuntimeException("Invalid ticket number");
//        }
//        switch (booking.getTicketStatus()) {
//            case "used" -> throw new RuntimeException("Ticket already used");
//            case "expired" -> throw new RuntimeException("Ticket already expired");
//            case "refund" -> throw new RuntimeException("You been refunded on this ticked");
//        }
//
//        Bus bus = busRouteService.getBusRepository().findByBusCode(checkinDto.getBusCode());
//
//        if(bus == null) {
//            throw new RuntimeException("Invalid bus code");
//        }
//
//        if(!bus.getRoute().getId().equals(booking.getRoute().getId())){
//            throw new RuntimeException("The ticket is not for this route");
//        }
////        checkin.setBus(bus);
//        booking.setTicketStatus("used");
//        checkin.setBooking(booking);
//        bookingRepository.save(booking);
//        checkinRepository.save(checkin);
//        ResponseDto responseDto = new ResponseDto();
//        responseDto.setResponse("Checkin successful");
//        return responseDto;
//    }
//}
