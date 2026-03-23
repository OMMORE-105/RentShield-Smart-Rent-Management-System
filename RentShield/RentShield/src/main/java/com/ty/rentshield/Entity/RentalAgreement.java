package com.ty.rentshield.Entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "rental_agreements")
public class RentalAgreement {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long agreementId;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private User tenant;
    
    @DecimalMin(value = "0.0", inclusive = false, message = "Rent must be greater than 0")
    @NotNull
    @Column(nullable = false)
    private BigDecimal rentAmount;
    
    @Column(nullable = false)
	private BigDecimal lateFeeAmount = BigDecimal.ZERO;
    
    @NotNull
    @Min(1)
    @Max(28)
    @Column(nullable = false)
    private Integer dueDay;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate agreementStart;
    
    @NotNull
    @Column(nullable = false)
    private LocalDate agreementEnd;
    
    @Column(nullable = false)
    private boolean active = true;


    @PrePersist
    @PreUpdate
    private void validateDates() {

        if (agreementStart == null || agreementEnd == null) {
            throw new IllegalArgumentException("Agreement dates cannot be null");
        }

        if (agreementEnd.isBefore(agreementStart)) {
            throw new RuntimeException("Agreement end date cannot be before start date");
        }
        
        if (ChronoUnit.MONTHS.between(agreementStart, agreementEnd) < 1) {
            throw new RuntimeException("Agreement must be at least 1 month long");
        }
    }
    
	///////////////////////////////////////////////////////////////////////

    
	public LocalDate getAgreementEnd() {
		return agreementEnd;
	}

	public void setAgreementEnd(LocalDate agreementEnd) {
		this.agreementEnd = agreementEnd;
	}

	public Long getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Long agreementId) {
		this.agreementId = agreementId;
	}

	public Property getProperty() {
		return property;
	}

	public void setProperty(Property property) {
		this.property = property;
	}

	public User getTenant() {
		return tenant;
	}

	public void setTenant(User tenant) {
		this.tenant = tenant;
	}

	public BigDecimal getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(BigDecimal rentAmount) {
		this.rentAmount = rentAmount;
	}

	public LocalDate getAgreementStart() {
		return agreementStart;
	}

	public void setAgreementStart(LocalDate agreementStart) {
		this.agreementStart = agreementStart;
	}
	
	public Integer getDueDay() {
		return dueDay;
	}

	public void setDueDay(Integer dueDay) {
		this.dueDay = dueDay;
	}  
    public BigDecimal getLateFeeAmount() {
		return lateFeeAmount;
	}

	public void setLateFeeAmount(BigDecimal lateFeeAmount) {
		this.lateFeeAmount = lateFeeAmount;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
    
}
