package com.ty.rentshield.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.rentshield.Entity.Payment;
import com.ty.rentshield.response.ResponseStructure;
import com.ty.rentshield.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	private final PaymentService paymentService;
	
	// Constructor Injection
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    //////////////////////////////// Create Monthly Payment  //////////////////////////////////////////////////////////////
    @PostMapping("/create/{agreementId}")
    public ResponseEntity<ResponseStructure<Payment>> createMonthlyPayment(@PathVariable Long agreementId, @RequestParam String month) {

        Payment payment = paymentService.createMonthlyPayment(
                agreementId,
                LocalDate.parse(month));

        ResponseStructure<Payment> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.CREATED.value());
        structure.setMessage("Monthly payment created successfully");
        structure.setData(payment);

        return new ResponseEntity<>(structure, HttpStatus.CREATED);
    }

    ///////////////////////////////////// Pay Rent  ////////////////////////////////////////////////////////////////////
    @PutMapping("/pay/{paymentId}")
    public ResponseEntity<ResponseStructure<Payment>> payRent(@PathVariable Long paymentId, @RequestParam BigDecimal amount) {

        Payment payment = paymentService.payRent(paymentId, amount);

        ResponseStructure<Payment> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Rent paid successfully");
        structure.setData(payment);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    /////////////////////////////////// Get Payments By Agreement  ////////////////////////////////////////////////////////
    @GetMapping("/agreement/{agreementId}")
    public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentsByAgreement(@PathVariable Long agreementId) {

        List<Payment> payments = paymentService.getPaymentsByAgreement(agreementId);

        ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Payments fetched successfully");
        structure.setData(payments);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }

    ////////////////////////////////////// Get Overdue Payments  ////////////////////////////////////////////////////////////////
    @GetMapping("/overdue")
    public ResponseEntity<ResponseStructure<List<Payment>>> getOverduePayments() {

        List<Payment> payments = paymentService.getOverduePayments();

        ResponseStructure<List<Payment>> structure = new ResponseStructure<>();
        structure.setStatusCode(HttpStatus.OK.value());
        structure.setMessage("Overdue payments fetched successfully");
        structure.setData(payments);

        return new ResponseEntity<>(structure, HttpStatus.OK);
    }
	
}
