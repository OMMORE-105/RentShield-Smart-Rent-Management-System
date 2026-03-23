package com.ty.rentshield.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.ty.rentshield.Entity.Payment;

public interface PaymentService {
	
	Payment createMonthlyPayment(Long agreementId, LocalDate rentMonth);

    Payment payRent(Long paymentId,  BigDecimal amount);

    List<Payment> getPaymentsByAgreement(Long agreementId);

    List<Payment> getOverduePayments();
    
}
