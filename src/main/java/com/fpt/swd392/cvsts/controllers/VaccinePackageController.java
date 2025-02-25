package com.fpt.swd392.cvsts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.fpt.swd392.cvsts.dto.response.VaccineBasicInfoDTO;
import com.fpt.swd392.cvsts.dto.response.VaccineServiceDTO;
import com.fpt.swd392.cvsts.services.IVacccinePackageService;
import com.fpt.swd392.cvsts.utils.ApiResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api")
public class VaccinePackageController {
    @Autowired
    private IVacccinePackageService vaccinePackageService;

    @GetMapping("/vaccine-packages")
    public ResponseEntity<ApiResponse<List<VaccineServiceDTO>>> getAllVaccinePackageDetails(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        ApiResponse<List<VaccineServiceDTO>> response = vaccinePackageService.getAllVaccinePackageDetails(page, size);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/vaccines-basic")
    public ResponseEntity<ApiResponse<VaccineBasicInfoDTO>> getVaccineBasicInfo() {
        VaccineBasicInfoDTO vaccineBasicInfo = vaccinePackageService.getVaccineBasicInfos();
        ApiResponse<VaccineBasicInfoDTO> response = new ApiResponse<>("200", vaccineBasicInfo, "Vaccine basic info retrieved successfully!");
        return ResponseEntity.ok(response);
    }
}
