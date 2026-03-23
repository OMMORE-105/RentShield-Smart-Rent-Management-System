package com.ty.rentshield.repository;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ty.rentshield.Entity.RentalAgreement;

@Repository
public interface RentalAgreementRepository extends JpaRepository<RentalAgreement, Long> {
	
//	List<RentalAgreement> findAll();
	
}
