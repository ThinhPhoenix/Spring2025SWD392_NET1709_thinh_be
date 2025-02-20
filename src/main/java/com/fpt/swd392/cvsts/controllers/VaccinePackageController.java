package com.fpt.swd392.cvsts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.dto.response.VaccineServiceDTO;
import com.fpt.swd392.cvsts.entities.VaccinePackage;
import com.fpt.swd392.cvsts.services.VaccinePackageService;
import com.fpt.swd392.cvsts.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class VaccinePackageController {
    @Autowired
    private VaccinePackageService vaccinePackageService;

    @GetMapping("/vaccine-packages")
    public ResponseEntity<ApiResponse<List<VaccineServiceDTO>>> getAllVaccinePackageDetails(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<VaccineServiceDTO>> response = vaccinePackageService.getAllVaccinePackageDetails(page, size);

        return ResponseEntity.ok(response);
    }

}
