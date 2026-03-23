package com.ty.rentshield.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.ty.rentshield.enumclass.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;


@Entity
@Table(name = "rent_payments")
public class Payment {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long payId;   
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "agreement_id", nullable = false)
	private RentalAgreement agreement;
	
	@NotNull
    @Column(nullable = false)
    private LocalDate rentMonth;   // Always store 1st of month
    
	@DecimalMin(value = "0.0", inclusive = false, message = "Payment must be greater than 0")
    @Column(nullable = true)
    private BigDecimal amountPaid;   
    
    @Column(nullable = true)
    private LocalDate paymentDate;   
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status = PaymentStatus.PENDING;   // PAID, PENDING, OVERDUE
    
    @Column(nullable = false)
    private BigDecimal lateFee = BigDecimal.ZERO;   // Optional late fee
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////

	public Long getPayId() {
		return payId;
	}

	public void setPayId(Long payId) {
		this.payId = payId;
	}	

	public RentalAgreement getAgreement() {
		return agreement;
	}

	public void setAgreement(RentalAgreement agreement) {
		this.agreement = agreement;
	}
	
	

	public LocalDate getRentMonth() {
		return rentMonth;
	}

	public void setRentMonth(LocalDate rentMonth) {
		this.rentMonth = rentMonth;
	}

	public BigDecimal getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(BigDecimal amountPaid) {
		this.amountPaid = amountPaid;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}
    
}