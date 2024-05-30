package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.service.NextOfKinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class NextOfKinController {
    private final NextOfKinService nextOfKinService;

    public NextOfKinController(NextOfKinService nextOfKinService) {
        this.nextOfKinService = nextOfKinService;
    }

    @GetMapping("/api/v1/next-of-kin-client-id/{userId}")
    public ResponseEntity<NextOfKin> findNextOfKinByUserId(@PathVariable("userId") String useId){
        return ResponseEntity.ok().body(nextOfKinService.findNextOfKinByUsrId(useId));
    }

    @GetMapping("/api/v1/next-of-kin/{id}")
    public ResponseEntity<NextOfKin> viewNextOfKin(@PathVariable("id") String id){
        return ResponseEntity.ok().body(nextOfKinService.findByNextOfKinById(id));
    }

    @PutMapping("/api/v1/update-next-of-kin/{id}")
    public ResponseEntity<String> updateNextOfKinById(@PathVariable("id") String id,
                                                      @RequestBody NextOfKin nextOfKin){
        return ResponseEntity.status(200).body(nextOfKinService.updateNextOfKin(id, nextOfKin));
    }
}
