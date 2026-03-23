package com.ty.rentshield.Entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "properties")
public class Property {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long propertyId;
	    
	    @NotBlank(message = "Address is required")
	    @Column(nullable = false)
	    private String address;
	    
	    @DecimalMin(value = "0.0", inclusive = false, message = "Rent must be greater than 0")
	    @NotNull
	    @Column(nullable = false)
	    private BigDecimal rentAmount;
	    
	    @Min(value = 1, message = "Due date must be between 1 and 31")
	    @Max(value = 31, message = "Due date must be between 1 and 31")
	    @NotNull
	    @Column(nullable = false)
	    private Integer dueDate; 
	    // Example: 4 = rent due on 5th of every month
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "landlord_id", nullable = false)
	    private User landlord;
	    ////////////////////////////////////////////////////////////////////////////////////////////
	    
		public Long getPropertyId() {
			return propertyId;
		}

		public void setPropertyId(Long propertyId) {
			this.propertyId = propertyId;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public BigDecimal getRentAmount() {
			return rentAmount;
		}

		public void setRentAmount(BigDecimal rentAmount) {
			this.rentAmount = rentAmount;
		}

		public Integer getDueDate() {
			return dueDate;
		}

		public void setDueDate(Integer dueDate) {
			this.dueDate = dueDate;
		}

		public User getLandlord() {
			return landlord;
		}

		public void setLandlord(User landlord) {
			this.landlord = landlord;
		}

	    
}
