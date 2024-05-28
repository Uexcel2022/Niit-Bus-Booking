package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;
import com.uexcel.busbooking.entity.*;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.repository.*;
import com.uexcel.busbooking.utils.Repos;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingCheckinServiceImp implements BookingCheckinService {
//    private final BusRouteService busRouteService;
//    private final BookingRepository bookingRepository;
//    private final ClientService clientService;
//    private final CheckinRepository checkinRepository;
//    private  final WalletService walletService;
    private final Repos repos;
    public BookingCheckinServiceImp(
//            BusRouteService busRouteService,
//                                    BookingRepository bookingRepository, ClientService clientService,
//                                    CheckinRepository checkinRepository, WalletService walletService,

                                    Repos repos
    ) {
//        this.busRouteService = busRouteService;
//        this.bookingRepository = bookingRepository;
//        this.clientService = clientService;
//        this.checkinRepository = checkinRepository;
//        this.walletService = walletService;
        this.repos = repos;
    }


    @Override
    public BookingInfoDto processBooking(String clientId, String routeId) {

        Booking bk = repos.getBookingRepository().findByClientId(clientId,routeId);
        if(bk != null){

            throw new CustomException("You have unused ticked on this route!!!"+" Ticked Number: "
                    +bk.getTicketNumber(),"404");
        }
        double price;
        double walletBalance;
        double newWalletBalance;
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        Booking booking = new Booking();
        Optional<Client> client = repos.getClientRepository().findById(clientId);
        Client u;
        if(client.isPresent()){
            u = client.get();
        } else throw new CustomException("User not found","404");

        ClientWallet wallet = repos.getClientWalletRepository()
                .findByClientId(clientId);
        walletBalance = wallet.getBalance();
        Optional<Route> route = repos.getRouteRepository().findById(routeId);

        Route r;
        if(route.isPresent()){
            r = route.get();
        } else throw new CustomException("Route not found","404");

        price = r.getPrice();

        if(walletBalance < price){
            throw new CustomException("Insufficient balance","402");
        }

        newWalletBalance = walletBalance - price;
        wallet.setBalance(newWalletBalance);
        booking.setRoute(r);
        booking.setClient(u);

        WalletTransaction walletTransaction = new WalletTransaction();
        walletTransaction.setBank("Wallet");
        walletTransaction.setTransactionType("Booking");
        walletTransaction.setAccountNumber(wallet.getWalletNumber());
        walletTransaction.setAmount(-r.getPrice());
        walletTransaction.setWallet(wallet);
        walletTransaction.setFullName(u.getFirstName() + " " + u.getLastName());
        walletTransaction.setTransactionDate(LocalDate.now());
        repos.getWallTransactionRepository().save(walletTransaction);


        booking.setTicketStatus("valid");

        bookingInfoDto.setFName(u.getFirstName());
        bookingInfoDto.setLName(u.getLastName());
        bookingInfoDto.setTickNumber(booking.getTicketNumber());
        bookingInfoDto.setRouteName(r.getRouteName());
        bookingInfoDto.setAmount(r.getPrice());
        bookingInfoDto.setBookingDate(booking.getBookingDate());
        bookingInfoDto.setTickStatus(booking.getTicketStatus());
        repos.getBookingRepository().save(booking);
        repos.getClientWalletRepository().save(wallet);
        return bookingInfoDto;

    }

    @Override
    public ResponseDto processCheckin(CheckinDto checkinDto) {
        Checkin checkin = new Checkin();
        Booking booking = repos.getBookingRepository().findByTicketNumber(checkinDto.getTicketNumber());

        if(booking == null){
            throw new CustomException("Ticket not found.","404");
        }
        switch (booking.getTicketStatus()) {
            case "used" -> throw new CustomException("Ticket already used.","400");
            case "expired" -> throw new CustomException("Ticket already expired.","400");
            case "refund" -> throw new CustomException("You been refunded on this ticked.","400");
        }

        Bus bus = repos.getBusRepository().findByBusCode(checkinDto.getBusCode());

        if(bus == null) {
            throw new CustomException("Bus not found.","404");
        }

        if(!bus.getRoute().getId().equals(booking.getRoute().getId())){
            throw new CustomException("The ticket is not for this route.","403");
        }

        checkin.setBusCode(bus.getBusCode());
        checkin.setBusCurrentRouteId(bus.getRoute().getId());
        booking.setTicketStatus("used");
        checkin.setBooking(booking);
        repos.getBookingRepository().save(booking);
        repos.getCheckinRepository().save(checkin);
        ResponseDto responseDto = new ResponseDto();
        responseDto.setResponse("Checkin successful.");
        return responseDto;
    }

    //Working on this
    public List<BookingInfoDto> findBookingByClientId(String clientId){
        List<Booking> booking = repos.getBookingRepository().findByClientId(clientId);

        if(booking!=null){
            return getBookingInfo(booking);
        }else throw new CustomException("Booking not found","404");
    }


    public List<BookingInfoDto> findByClientIdAndTicketStatus(String clientId, String status){
        List<Booking> booking = repos.getBookingRepository().findByClientIdAndTicketStatus(clientId,status);

        if(booking!=null){
            return getBookingInfo(booking);
        }else throw new CustomException("Booking not found","404");
    }


//    public List<BusCheckinInfoDto> findBusesOnRoute(BusCheckinQueryDto busCheckinQueryDto) {
//        List<Checkin> checkin = checkinRepository.findByBusCurrentRouteId(
//                busCheckinQueryDto.getBusCurrentRouteId());
//        if(checkin == null){
//            throw new CustomException("Route not found.","404");
//        }
//
//        return filterResultSet(checkin);
//    }
//
//    public List<BusCheckinInfoDto> findBusesOnRouteByDate(BusCheckinQueryDto busCheckinQueryDto) {
//        List<Checkin> checkin = checkinRepository.findByBusCurrentRouteIdAndCheckinDate(
//                busCheckinQueryDto.getBusCurrentRouteId(), busCheckinQueryDto.getDate());
//        if(checkin == null){
//            throw new CustomException("Not found.","404");
//        }
//        return filterResultSet(checkin);
//    }
//
//    public List<BusCheckinInfoDto> findBusRoutes(BusCheckinQueryDto busCheckinQueryDto) {
//        List<Checkin> checkin = checkinRepository.findByBusCode(busCheckinQueryDto.getBusCode());
//        if(checkin == null){
//            throw new CustomException("Not found.","404");
//        }
//        return filterResultSet(checkin);
//    }
//
//    @Override
//    public List<BusCheckinInfoDto> findBusRoutesByDay(BusCheckinQueryDto busCheckinQueryDto) {
//        List<Checkin> checkin = checkinRepository.findByBusCodeAndCheckinDate(
//                 busCheckinQueryDto.getBusCode(),busCheckinQueryDto.getDate());
//        if(checkin == null){
//            throw new CustomException("Not found.","404");
//        }
//        return filterResultSet(checkin);
//    }
//
//
//    public  void findBooking(LocalDate date1, LocalDate date2){
//    }
//
//
//
//    private static   List<BusCheckinInfoDto> filterResultSet(List<Checkin> checkin){
//        List<BusCheckinInfoDto> busCheckinInfo = new ArrayList<>();
//        for(Checkin checkin1 : checkin){
//            BusCheckinInfoDto busCheckinInfoDto = new BusCheckinInfoDto();
//            busCheckinInfoDto.setBookingId(checkin1.getBooking().getId());
//            busCheckinInfoDto.setBusCode(checkin1.getBusCode());
//            busCheckinInfoDto.setRouteName(checkin1.getBooking().getRoute().getRouteName());
//            busCheckinInfoDto.setCheckinDate(checkin1.getCheckinDate());
//            busCheckinInfo.add(busCheckinInfoDto);
//        }
//        return busCheckinInfo;
//    }


    public static  List<BookingInfoDto> getBookingInfo(List<Booking> bookingList){
        List<BookingInfoDto> bookingInfo = new ArrayList<>();
        for(Booking clientObj : bookingList){
            BookingInfoDto bookingInfoDto = new BookingInfoDto();
            bookingInfoDto.setFName(clientObj.getClient().getFirstName());
            bookingInfoDto.setLName(clientObj.getClient().getLastName());
            bookingInfoDto.setTickNumber(clientObj.getTicketNumber());
            bookingInfoDto.setRouteName(clientObj.getRoute().getRouteName());
            bookingInfoDto.setAmount(clientObj.getRoute().getPrice());
            bookingInfoDto.setBookingDate(clientObj.getBookingDate());
            bookingInfoDto.setTickStatus(clientObj.getTicketStatus());
            bookingInfo.add(bookingInfoDto);
        }
        return bookingInfo;
    }
}




