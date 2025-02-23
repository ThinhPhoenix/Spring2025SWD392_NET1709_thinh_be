package com.fpt.swd392.cvsts.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.services.ITransactionService;

@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    ITransactionService transactionService;

    public ManagerController(ITransactionService transactionService) {
        this.transactionService = transactionService;
    }
    
    @PatchMapping("/accept-refund-request")
    public ResponseEntity<?> acceptRefund(@RequestBody String appointmentId) {
        try {
            transactionService.acceptRefundRequest(appointmentId);
            return ResponseEntity.ok("Refund request accept successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error accept refund request: " + e.getMessage());
        }
    }
}
