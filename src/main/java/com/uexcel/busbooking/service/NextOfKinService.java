package com.uexcel.busbooking.service;

import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.repository.NextOfKinRepository;

public interface NextOfKinService {
    NextOfKinRepository getNextOfKinRepository();

    NextOfKin findNextOfKinByUsrId(Long id);
}
