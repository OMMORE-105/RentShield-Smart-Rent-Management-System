package com.ty.rentshield.service;

import java.util.List;

import com.ty.rentshield.Entity.RentalAgreement;

public interface RentalAgreementService {
	
	RentalAgreement createAgreement(RentalAgreement agreement);

    RentalAgreement getAgreementById(Long id);

    List<RentalAgreement> getAllAgreements();

    RentalAgreement updateAgreement(Long id, RentalAgreement agreement);

    void deleteAgreement(Long id);
	
}
