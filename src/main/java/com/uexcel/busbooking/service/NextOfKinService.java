package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;

public interface NextOfKinService {
    NextOfKinRepository getNextOfKinRepository();

    NextOfKin findNextOfKinByUsrId(String id);

    NextOfKin findByNextOfKinById(String id);

    String updateNextOfKin(String id, NextOfKin nextOfKin);

}
