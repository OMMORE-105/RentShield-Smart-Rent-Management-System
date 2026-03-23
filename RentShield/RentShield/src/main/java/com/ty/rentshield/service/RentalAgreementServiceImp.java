package com.ty.rentshield.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ty.rentshield.Entity.RentalAgreement;
import com.ty.rentshield.repository.RentalAgreementRepository;

@Service
public class RentalAgreementServiceImp implements RentalAgreementService{
	
	@Autowired
    private RentalAgreementRepository rentalAgreementRepository;

    @Override
    public RentalAgreement createAgreement(RentalAgreement agreement) {
        return rentalAgreementRepository.save(agreement);
    }

    @Override
    public RentalAgreement getAgreementById(Long id) {
        return rentalAgreementRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agreement not found with id: " + id));
    }

    @Override
    public List<RentalAgreement> getAllAgreements() {
        return rentalAgreementRepository.findAll();
    }

    @Override
    public RentalAgreement updateAgreement(Long id, RentalAgreement agreement) {

        RentalAgreement existing = getAgreementById(id);

        existing.setProperty(agreement.getProperty());
        existing.setTenant(agreement.getTenant());
        existing.setRentAmount(agreement.getRentAmount());
        existing.setLateFeeAmount(agreement.getLateFeeAmount());
        existing.setDueDay(agreement.getDueDay());
        existing.setAgreementStart(agreement.getAgreementStart());
        existing.setAgreementEnd(agreement.getAgreementEnd());
        existing.setActive(agreement.isActive());

        return rentalAgreementRepository.save(existing);
    }

    @Override
    public void deleteAgreement(Long id) {

        RentalAgreement agreement = getAgreementById(id);
        rentalAgreementRepository.delete(agreement);
    }
	
}
