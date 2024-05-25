package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.service.BusRouteService;
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


    @GetMapping("/api/v1/find_bus_by_code/{busCode}")
    public ResponseEntity<Bus> findBusByCode(@PathVariable("busCode") String busCode) {
        return ResponseEntity.ok().body(busRouteService.findBusByCode(busCode));
    }

    @GetMapping("/api/v1/find_all_bus")
    public ResponseEntity<List<Bus>> findALLBus() {
        return ResponseEntity.ok().body(busRouteService.findAllBus());
    }

    @GetMapping("/api/v1/find_route_name/{routeName}")
    public ResponseEntity<Route> findRoutByName(@PathVariable("routeName") String routeName) {
        return ResponseEntity.ok().body(busRouteService.findRoutByName(routeName));
    }

    @GetMapping("/api/v1/find_all_route")
    public ResponseEntity<List<Route>> findALLRoute() {
        return ResponseEntity.ok().body(busRouteService.findAllRoute());
    }


    @PutMapping("/api/v1/update_bus/{id}")
    public ResponseEntity<ResponseDto> updateBus(@PathVariable("id") String busId,
                                                 @RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.ok().body(busRouteService.updateBus(busId,busRouteDto));
    }

    @PutMapping("/api/v1/update_bus_route/{busCode}/{routeName}")
    public ResponseEntity<ResponseDto> updateBusRoute(@PathVariable("busCode") String busCode,
                                                 @PathVariable("routeName") String routeName) {
        return ResponseEntity.status(200).body(busRouteService.updateBusRoute(busCode,routeName));
    }

    @DeleteMapping("/api/v1/bus/{id}")
    public ResponseEntity<ResponseDto> deleteBus(@PathVariable("id") Long busId) {
        return ResponseEntity.status(204).body(null);
    }

    @PutMapping("/api/v1/update_route/{id}")
    public ResponseEntity<ResponseDto> updateRoute(@PathVariable("id") String routeId,
                                                 @RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.ok().body(busRouteService.updateRoute(routeId,busRouteDto));
    }

    @DeleteMapping("/api/v1/delete_route/{id}")
    public ResponseEntity<ResponseDto> deleteRout(@PathVariable("id") Long routeId) {
        return ResponseEntity.status(204).body(null);
    }
}
