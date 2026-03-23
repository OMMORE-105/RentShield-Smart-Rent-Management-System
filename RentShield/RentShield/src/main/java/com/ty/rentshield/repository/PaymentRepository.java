package com.ty.rentshield.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.rentshield.Entity.Payment;
import com.ty.rentshield.Entity.RentalAgreement;
import com.ty.rentshield.enumclass.PaymentStatus;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	// check duplicate monthly payment
	boolean existsByAgreementAndRentMonth(RentalAgreement agreement, LocalDate rentMonth);
    
    // get all payments of an agreement
    List<Payment> findByAgreement(RentalAgreement agreement);

    // get overdue payments
    List<Payment> findByStatus(PaymentStatus status);
	
}