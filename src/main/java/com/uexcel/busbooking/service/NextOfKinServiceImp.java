package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

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

    @Override
    public NextOfKin findByNextOfKinById(Long id) {
        Optional<NextOfKin> nextOfKin = nextOfKinRepository.findById(id);
        if (nextOfKin.isPresent()) {
            return nextOfKin.get();
        }else  throw new NoSuchElementException("Next of kin not found");
    }

    @Override
    public NextOfKin updateNextOfKin(Long id, NextOfKin updatedNextOfKin) {

        Optional<NextOfKin> nextOfKinOptional = nextOfKinRepository.findById(id);
        if (nextOfKinOptional.isPresent()) {
            NextOfKin oldNextOfKin = nextOfKinOptional.get();
            oldNextOfKin.setNFirstName(updatedNextOfKin.getNFirstName());
            oldNextOfKin.setNLastName(updatedNextOfKin.getNLastName());
            oldNextOfKin.setAddress(updatedNextOfKin.getAddress());
            oldNextOfKin.setLga(updatedNextOfKin.getLga());
            oldNextOfKin.setStreet(updatedNextOfKin.getStreet());
            oldNextOfKin.setState(updatedNextOfKin.getState());
            oldNextOfKin.setNPhoneNumber(updatedNextOfKin.getNPhoneNumber());
            nextOfKinRepository.save(oldNextOfKin);
            return oldNextOfKin;
        } else throw new NoSuchElementException("Update failed");
    }

}
