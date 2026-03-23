package com.ty.rentshield.scheduler;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ty.rentshield.Entity.RentalAgreement;
import com.ty.rentshield.repository.RentalAgreementRepository;
import com.ty.rentshield.service.EmailService;

@Component
public class RentReminderScheduler {
	
	private final RentalAgreementRepository agreementRepository;
	private final EmailService emailService;

    public RentReminderScheduler(RentalAgreementRepository agreementRepository , EmailService emailService) {
    	this.agreementRepository = agreementRepository;
    	this.emailService = emailService;
    }

    // Runs automatically on 1st day of every month
    @Scheduled(cron = "0 0 0 1 * ?")
    public void sendRentReminders() {

        System.out.println("Checking for overdue rent payments...");
        
        List<RentalAgreement> agreements = agreementRepository.findAll();

		for (RentalAgreement agreement : agreements) {

			String tenantEmail = agreement.getTenant().getEmail();
            String tenantName = agreement.getTenant().getUserName();
            String propertyAddress = agreement.getProperty().getAddress();

            emailService.sendRentReminder(
                    tenantEmail,
                    tenantName,
                    propertyAddress
            );
		}
		System.out.println("Reminder check completed.");
	}
        
	
}