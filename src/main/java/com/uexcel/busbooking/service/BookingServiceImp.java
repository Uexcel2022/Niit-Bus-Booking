package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.BookingInfoDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.entity.Booking;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.entity.User;
import com.uexcel.busbooking.repository.BookingRepository;
import com.uexcel.busbooking.repository.BusRepository;
import com.uexcel.busbooking.repository.RouteRepository;
import com.uexcel.busbooking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImp implements BookingService {
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    public BookingServiceImp(BusRepository busRepository,
                             RouteRepository routeRepository,
                             BookingRepository bookingRepository,
                             UserRepository userRepository
                             ) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
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
        double price;
        double walletBalance;
        double newWalletBalance;
        BookingInfoDto bookingInfoDto = new BookingInfoDto();
        Booking booking = new Booking();
        Optional<User> user = userRepository.findById(userId);
        user.ifPresent(booking::setUser);

        Booking bk = bookingRepository.findByUserId(userId,routeId);
        if(bk != null){

            throw new RuntimeException("You have unused ticked on this route!!!"+" Ticked Number: "
                    +bk.getTicketNumber());
        }

        walletBalance = user.get().getUserWallet().getBalance();

        Optional<Route> route = routeRepository.findById(routeId);
        route.ifPresent(booking::setRoute);

        price = route.get().getPrice();

        if(walletBalance < price){
            throw new RuntimeException("Insufficient balance");
        }

        newWalletBalance = walletBalance - price;
        user.get().getUserWallet().setBalance(newWalletBalance);

        userRepository.save(user.get());

        booking.setTicketStatus("valid");
        bookingRepository.save(booking);
        bookingInfoDto.setFName(user.get().getFirstName());
        bookingInfoDto.setLName(user.get().getLastName());
        bookingInfoDto.setTickNumber(booking.getTicketNumber());
        bookingInfoDto.setRouteName(route.get().getRouteName());
        bookingInfoDto.setAmount(route.get().getPrice());
        bookingInfoDto.setBookingDate(booking.getBookingDate());
        bookingInfoDto.setTickStatus(booking.getTicketStatus());
        return bookingInfoDto;

    }
}
