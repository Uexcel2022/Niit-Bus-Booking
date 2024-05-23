package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.service.BusRouteService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BusRouteController {
    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @PostMapping("/api/v1/add_bus_route")
    public ResponseEntity<Bus> addBus(@RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.status(201).body(busRouteService.addBus(busRouteDto));
    }


    @PutMapping("/apa/v1/find_bus/{busCode}")
    public ResponseEntity<Bus> findRoutByCode(@PathVariable("busCode") String busCode) {
        return null;
    }

    @PutMapping("/apa/v1/find_all_bus")
    public ResponseEntity<List<Bus>> findALLBus() {
        return null;
    }

    @PutMapping("/apa/v1/find_bus/{routeName}")
    public ResponseEntity<Bus> findRoutByName(@PathVariable("routeName") String routeName) {
        return null;
    }

    @PutMapping("/apa/v1/find_all_bus")
    public ResponseEntity<List<Bus>> findALLRoute() {
        return null;
    }


    @PutMapping("/apa/v1/update_bus/{id}")
    public ResponseEntity<ResponseDto> updateBus(@PathVariable("id") Long busId,
                                                 @RequestBody BusRouteDto busRouteDto) {
        return null;
    }

    @PutMapping("/apa/v1/bus/{id}")
    public ResponseEntity<ResponseDto> deleteBus(@PathVariable("id") Long busId) {
        return ResponseEntity.status(204).body(null);
    }

    @PutMapping("/apa/v1/update_route/{id}")
    public ResponseEntity<ResponseDto> updateRoute(@PathVariable("id") Long routeId,
                                                 @RequestBody BusRouteDto busRouteDto) {
        return null;
    }

    @PutMapping("/apa/v1/delete_route/{id}")
    public ResponseEntity<ResponseDto> deleteRout(@PathVariable("id") Long routeId) {
        return ResponseEntity.status(204).body(null);
    }
}
