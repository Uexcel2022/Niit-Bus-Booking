package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;

public interface NextOfKinService {
    NextOfKinRepository getNextOfKinRepository();

    NextOfKin findNextOfKinByUsrId(Long id);

    NextOfKin findByNextOfKinById(Long id);

    ResponseDto updateNextOfKin(Long id, NextOfKin nextOfKin);

}
