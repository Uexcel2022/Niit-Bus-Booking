package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.*;
import com.uexcel.busbooking.entity.*;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookingCheckinServiceImp implements BookingCheckinService {
    private final Repos repos;
    public BookingCheckinServiceImp(Repos repos) {
        this.repos = repos;
    }

    @Override
    @Transactional
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
        walletTransaction.setFullName(u.getFullName());
        walletTransaction.setTransactionDate(LocalDate.now());
        repos.getWallTransactionRepository().save(walletTransaction);


        booking.setTicketStatus("valid");

        bookingInfoDto.setFullName(u.getFullName());
//        bookingInfoDto.setGender(u.getGender());
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
    @Transactional
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

        if(!booking.isEmpty()){
            return getBookingInfo(booking);
        }else throw new CustomException("No booking found","404");
    }

    @Override
    public List<BookingInfoDto> findByClientIdAndTicketStatus(String clientId, String status){
        List<Booking> booking = repos.getBookingRepository().findByClientIdAndTicketStatus(clientId,status);

        if(!booking.isEmpty()){
            return getBookingInfo(booking);
        }else throw new CustomException("No valid ticket booking found","404");
    }

    @Override
    public List<BookingInfoDto> findAllTicketByStatusAndRouteName(SearchingTicketDto searchingTicketDto){
        Route route = repos.getRouteRepository().findByRouteName(searchingTicketDto.getRouteName());
        if(route == null){
            throw new CustomException("Route not found.","404");
        }

        List<Booking> booking = repos.getBookingRepository().findByTicketStatusAndRoutName(searchingTicketDto.getStatus(),route.getId());

        if(!booking.isEmpty()){
            return getBookingInfo(booking);
        }else throw new CustomException("No valid ticket booking found.","404");
    }

    @Override
    public List<BookingInfoDto> findAllTicketByStatusAndRouteNameAndPhone(SearchingTicketDto searchingTicketDto){
        Route route = repos.getRouteRepository().findByRouteName(searchingTicketDto.getRouteName());
        if(route == null){
            throw new CustomException("Route not found.","404");
        }

        List<Booking> booking = repos.getBookingRepository().findByTicketStatusAndRoutName(searchingTicketDto.getStatus(),route.getId());
        List<Booking> returnB = new ArrayList<>();
        if(!booking.isEmpty()){
            for(Booking b : booking){
                if(b.getClient().getPhoneNumber().equals(searchingTicketDto.getPhone())){
                    returnB.add(b);
                    return getBookingInfo(returnB);
                }
            }
        }
        throw new CustomException("No "+ searchingTicketDto.getStatus() +" ticket found.","404");
    }


    public static  List<BookingInfoDto> getBookingInfo(List<Booking> bookingList){
        List<BookingInfoDto> bookingInfo = new ArrayList<>();
        for(Booking clientObj : bookingList){
            BookingInfoDto bookingInfoDto = new BookingInfoDto();
            bookingInfoDto.setFullName(clientObj.getClient().getFullName());
//            bookingInfoDto.setGender(clientObj.getClient().getGender());
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




