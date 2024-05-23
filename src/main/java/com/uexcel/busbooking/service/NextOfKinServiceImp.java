package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class NextOfKinServiceImp implements NextOfKinService {
    private final NextOfKinRepository nextOfKinRepository;
    public NextOfKinServiceImp(NextOfKinRepository nextOfKinRepository) {
        this.nextOfKinRepository = nextOfKinRepository;
    }
    @Override
    public NextOfKinRepository getNextOfKinRepository() {
        return nextOfKinRepository;
    }

    @Override
    public NextOfKin findNextOfKinByUsrId(Long userId) {
        NextOfKin nextOfKin = nextOfKinRepository.findByUserId(userId);
        if(nextOfKin != null) {
            return nextOfKin;
        } else throw new NoSuchElementException("next of kin not found");
    }
}
