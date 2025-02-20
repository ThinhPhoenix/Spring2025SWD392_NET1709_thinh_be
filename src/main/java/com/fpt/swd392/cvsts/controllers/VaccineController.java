package com.fpt.swd392.cvsts.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.entities.Vaccine;
import com.fpt.swd392.cvsts.services.IVaccineService;
import com.fpt.swd392.cvsts.utils.ApiResponse;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api")
public class VaccineController {
    private IVaccineService vaccineService;

    public VaccineController(IVaccineService vaccineService) {
        this.vaccineService = vaccineService;
    }

    @PostMapping("/vaccine")
    public ResponseEntity<ApiResponse<Vaccine>> postMethodName(@RequestBody Vaccine entity) {
        Vaccine vaccine = vaccineService.createVaccine(entity);
        ApiResponse<Vaccine> response = new ApiResponse<>("201", vaccine, "Vaccine created successfully!");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
}
