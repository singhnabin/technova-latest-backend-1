package com.pawan.ecommerce.ecommerce.controller;

import com.pawan.ecommerce.ecommerce.dto.PaymentRequest;
import com.pawan.ecommerce.ecommerce.service.PaymentService;
import com.stripe.exception.StripeException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/technova")
@Slf4j
@CrossOrigin(value = "*")
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping("/payment")
    public ResponseEntity<?> confirmPayment(@RequestBody PaymentRequest paymentRequest) throws StripeException {
       JSONObject jsonObject= paymentService.makePayment(paymentRequest);
        return ResponseEntity.status(HttpStatus.OK).body(jsonObject.toString());
    }
}
