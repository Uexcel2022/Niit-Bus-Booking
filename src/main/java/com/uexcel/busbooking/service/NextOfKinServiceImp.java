package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.utils.Validation;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class NextOfKinServiceImp implements NextOfKinService {
    private final NextOfKinRepository nextOfKinRepository;
    private  final Validation validation;
    public NextOfKinServiceImp(NextOfKinRepository nextOfKinRepository, Validation validation) {
        this.nextOfKinRepository = nextOfKinRepository;
        this.validation = validation;
    }
    @Override
    public NextOfKinRepository getNextOfKinRepository() {
        return nextOfKinRepository;
    }

    @Override
    public NextOfKin findNextOfKinByUsrId(String userId) {
        NextOfKin nextOfKin = nextOfKinRepository.findByUserId(userId);
        if(nextOfKin != null) {
            return nextOfKin;
        } else throw new NoSuchElementException("next of kin not found");
    }

    @Override
    public NextOfKin findByNextOfKinById(String id) {
        Optional<NextOfKin> nextOfKin = nextOfKinRepository.findById(id);
        if (nextOfKin.isPresent()) {
            return nextOfKin.get();
        }else  throw new NoSuchElementException("Next of kin not found");
    }

    @Override
    public ResponseDto updateNextOfKin(String id, NextOfKin updatedNextOfKin) {

        Optional<NextOfKin> nextOfKinOptional = nextOfKinRepository.findById(id);
        if (nextOfKinOptional.isPresent()) {
            NextOfKin toUpdateNextOfKin = nextOfKinOptional.get();

            if(validation.checkName(updatedNextOfKin.getNFirstName())) {
                toUpdateNextOfKin.setNFirstName(updatedNextOfKin.getNFirstName());
            }

            if(validation.checkName(updatedNextOfKin.getNLastName())) {
                toUpdateNextOfKin.setNLastName(updatedNextOfKin.getNLastName());
            }

            if(validation.checkNullBlank(updatedNextOfKin.getAddress())) {
                toUpdateNextOfKin.setAddress(updatedNextOfKin.getAddress());
            }
            if(validation.checkNullBlank(updatedNextOfKin.getLga())) {
                toUpdateNextOfKin.setLga(updatedNextOfKin.getLga());
            }

            if(validation.checkNullBlank(updatedNextOfKin.getState())) {
                toUpdateNextOfKin.setState(updatedNextOfKin.getState());
            }
            if(!validation.checkPhone(updatedNextOfKin.getNPhoneNumber())) {
                toUpdateNextOfKin.setNPhoneNumber(updatedNextOfKin.getNPhoneNumber());
            }
            nextOfKinRepository.save(toUpdateNextOfKin);
            ResponseDto responseDto = new ResponseDto();
            responseDto.setResponse("Updated may be successful if the information entered is valid." +
                    "Please cross check; otherwise try again");
            return responseDto;
        } else throw new NoSuchElementException("Update failed");
    }



}
