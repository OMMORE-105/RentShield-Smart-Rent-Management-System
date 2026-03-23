package com.ty.rentshield.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ty.rentshield.Entity.Payment;
import com.ty.rentshield.Entity.RentalAgreement;
import com.ty.rentshield.enumclass.PaymentStatus;
import com.ty.rentshield.exception.ResourceNotFoundException;
import com.ty.rentshield.repository.PaymentRepository;
import com.ty.rentshield.repository.RentalAgreementRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {
	
	private final PaymentRepository paymentRepository;
    private final RentalAgreementRepository agreementRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository, RentalAgreementRepository agreementRepository) {
        this.paymentRepository = paymentRepository;
        this.agreementRepository = agreementRepository;
    }

    ////////////////////////////////  Create Monthly Payment  /////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public Payment createMonthlyPayment(Long agreementId, LocalDate rentMonth) {

        RentalAgreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new ResourceNotFoundException("Agreement not found with id: " + agreementId));

        // Always store first day of month
        LocalDate normalizedMonth = rentMonth.withDayOfMonth(1);

        // 🚫 Prevent duplicate month entry
        boolean exists = paymentRepository
                .existsByAgreementAndRentMonth(agreement, normalizedMonth);

        if (exists) {
            throw new RuntimeException("Payment already created for this month");
        }
        
        Payment payment = new Payment();
        payment.setAgreement(agreement);
        payment.setRentMonth(normalizedMonth);
        payment.setStatus(PaymentStatus.PENDING);
        payment.setLateFee(BigDecimal.ZERO);

        return paymentRepository.save(payment);
    }

    ///////////////////////////////// Pay Rent  /////////////////////////////////////////////////////////////
    @Override
    public Payment payRent(Long paymentId, BigDecimal amount) {

        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new ResourceNotFoundException("Payment not found with id: " + paymentId));

        RentalAgreement agreement = payment.getAgreement();
        LocalDate rentMonth = payment.getRentMonth();

        // Safe due date calculation
        int dueDay = agreement.getDueDay();
        int lastDay = rentMonth.lengthOfMonth();

        LocalDate monthlyDueDate =
                rentMonth.withDayOfMonth(Math.min(dueDay, lastDay));

        LocalDate today = LocalDate.now();

        payment.setAmountPaid(amount);
        payment.setPaymentDate(today);
        payment.setStatus(PaymentStatus.PAID);

        // Late fee logic
        if (today.isAfter(monthlyDueDate)) {
            payment.setLateFee(agreement.getLateFeeAmount());
        } else {
            payment.setLateFee(BigDecimal.ZERO);
        }

        return paymentRepository.save(payment);
    }

    //////////////////////////////////////////// Get Payments By Agreement  //////////////////////////////////////////
    @Override
    public List<Payment> getPaymentsByAgreement(Long agreementId) {

        RentalAgreement agreement = agreementRepository.findById(agreementId)
                .orElseThrow(() -> new ResourceNotFoundException("Agreement not found with id: " + agreementId));

        return paymentRepository.findByAgreement(agreement);
    }

    ///////////////////////////////////////////////  Get Overdue Payments  //////////////////////////////////////////////////////
    @Override
    public List<Payment> getOverduePayments() {

        return paymentRepository.findByStatus(PaymentStatus.OVERDUE);
    }
	
}
