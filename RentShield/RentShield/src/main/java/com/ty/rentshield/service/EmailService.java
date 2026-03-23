package com.ty.rentshield.service;

public interface EmailService {
	
	void sendRentReminder(String toEmail, String tenantName, String propertyAddress);

	
}
