package com.uexcel.busbooking;

import com.uexcel.busbooking.entity.Signup;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BusBookingController {
    @PatchMapping("/v")
    public  Signup signup() {
        return  null;
    }
}
