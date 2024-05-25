package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;

public interface NextOfKinService {
    NextOfKinRepository getNextOfKinRepository();

    NextOfKin findNextOfKinByUsrId(String id);

    NextOfKin findByNextOfKinById(String id);

    ResponseDto updateNextOfKin(String id, NextOfKin nextOfKin);

}
