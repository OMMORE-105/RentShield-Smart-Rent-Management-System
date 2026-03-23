package com.ty.rentshield.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.rentshield.Entity.RentalAgreement;
import com.ty.rentshield.service.RentalAgreementService;

@RestController
@RequestMapping("/rentalagreements")
@Validated
public class RentalAgreementController {
	
	@Autowired
    private RentalAgreementService rentalAgreementService;

	/////////////////////////////////////  create  /////////////////////////////////////////////////////////////////////////
    @PostMapping("/create")
    public RentalAgreement createAgreement(@RequestBody RentalAgreement agreement) {
        return rentalAgreementService.createAgreement(agreement);
    }
    
    /////////////////////////////////////////////////  get by Id  ///////////////////////////////////////////
    @GetMapping("/{id}")
    public RentalAgreement getAgreementById(@PathVariable Long id) {
        return rentalAgreementService.getAgreementById(id);
    }
    
    //////////////////////////////////////// get All  ////////////////////////////////////////////////////////////
    @GetMapping("/all")
    public List<RentalAgreement> getAllAgreements() {
        return rentalAgreementService.getAllAgreements();
    }
    
    ////////////////////////////////////////////  update  /////////////////////////////////////////////////////////
    @PutMapping("/{id}")
    public RentalAgreement updateAgreement(@PathVariable Long id, @RequestBody RentalAgreement agreement) {
        return rentalAgreementService.updateAgreement(id, agreement);
    }
    
    ////////////////////////////////////////////////  Delete  ////////////////////////////////////////////////////////////
    @DeleteMapping("/{id}")
    public String deleteAgreement(@PathVariable Long id) {
        rentalAgreementService.deleteAgreement(id);
        return "Agreement deleted successfully";
    }
	
}
