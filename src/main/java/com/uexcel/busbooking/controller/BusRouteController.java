package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.BusCheckinInfoDto;
import com.uexcel.busbooking.dto.BusCheckinQueryDto;
import com.uexcel.busbooking.dto.BusRouteDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Bus;
import com.uexcel.busbooking.entity.Route;
import com.uexcel.busbooking.service.BusRouteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/v1")
public class BusRouteController {
    private final BusRouteService busRouteService;

    public BusRouteController(BusRouteService busRouteService) {
        this.busRouteService = busRouteService;
    }

    @PostMapping("add-bus")
    public ResponseEntity<String> addBus(@RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.status(201).body(busRouteService.addBus(busRouteDto));
    }

    @PostMapping("add-route")
    public ResponseEntity<String> addRoute(@RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.status(201).body(busRouteService.addRout(busRouteDto));
    }


    @GetMapping("find-bus-by-code/{busCode}")
    public ResponseEntity<Bus> findBusByCode(@PathVariable("busCode") String busCode) {
        return ResponseEntity.ok().body(busRouteService.findBusByCode(busCode));
    }

    @GetMapping("find-all-bus")
    public ResponseEntity<List<Bus>> findALLBus() {
        return ResponseEntity.ok().body(busRouteService.findAllBus());
    }

    @GetMapping("find-route-name/{routeName}")
    public ResponseEntity<Route> findRoutByName(@PathVariable("routeName") String routeName) {
        return ResponseEntity.ok().body(busRouteService.findRoutByName(routeName));
    }

    @GetMapping("find-all-route")
    public ResponseEntity<List<Route>> findALLRoute() {
        return ResponseEntity.ok().body(busRouteService.findAllRoute());
    }


    @PutMapping("update-bus/{id}")
    public ResponseEntity<String> updateBus(@PathVariable("id") String busId,
                                            @RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.ok().body(busRouteService.updateBus(busId,busRouteDto));
    }

    @PutMapping("update-bus-route/{busCode}/{routeName}")
    public ResponseEntity<String> updateBusRoute(@PathVariable("busCode") String busCode,
                                                 @PathVariable("routeName") String routeName) {
        return ResponseEntity.status(200).body(busRouteService.updateBusRoute(busCode,routeName));
    }

    @DeleteMapping("bus/{id}")
    public ResponseEntity<ResponseDto> deleteBus(@PathVariable("id") String busId) {
        return ResponseEntity.status(204).body(null);
    }

    @PutMapping("update-route/{id}")
    public ResponseEntity<String> updateRoute(@PathVariable("id") String routeId,
                                              @RequestBody BusRouteDto busRouteDto) {
        return ResponseEntity.ok().body(busRouteService.updateRoute(routeId,busRouteDto));
    }

    @DeleteMapping("delete-route/{id}")
    public ResponseEntity<ResponseDto> deleteRout(@PathVariable("id") String routeId) {
        return ResponseEntity.status(204).body(null);
    }

    @PostMapping("find-buses-on-route-by-date")
    public ResponseEntity<List<BusCheckinInfoDto>> findBusesOnRouteByDate(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
        return ResponseEntity.ok().body(busRouteService.findBusesOnRouteByDate(busCheckinQueryDto));
    }

    @PostMapping("find-buses-on-route")
    public ResponseEntity<List<BusCheckinInfoDto>> findBusesOnRoute(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
        return ResponseEntity.ok().body(busRouteService.findBusesOnRoute(busCheckinQueryDto));
    }

    @PostMapping("find-bus-routes")
    public ResponseEntity<List<BusCheckinInfoDto>> findBusRoutes(@RequestBody BusCheckinQueryDto busCheckinQueryDto){
        return ResponseEntity.ok().body(busRouteService.findBusRoutes(busCheckinQueryDto));
    }

    @PostMapping("find-bus-routes-day")
    public ResponseEntity<List<BusCheckinInfoDto>> findBusRoutesByDay(@RequestBody BusCheckinQueryDto busCheckinQueryDto) {
        return ResponseEntity.ok().body(busRouteService.findBusRoutesByDay(busCheckinQueryDto));
    }

}
