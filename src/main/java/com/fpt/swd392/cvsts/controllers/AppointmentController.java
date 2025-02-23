package com.fpt.swd392.cvsts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.dto.request.SigninRequest;
import com.fpt.swd392.cvsts.dto.request.VaccineRegistrationRequest;
import com.fpt.swd392.cvsts.dto.response.JwtResponse;
import com.fpt.swd392.cvsts.entities.Appointment;
import com.fpt.swd392.cvsts.services.IAppointmentService;
import com.fpt.swd392.cvsts.services.UserDetailsImpl;
import com.fpt.swd392.cvsts.utils.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AppointmentController {
    @Autowired
    private IAppointmentService appointmentService;

    @PostMapping("/appointment")
    public ResponseEntity<ApiResponse<Appointment>> registerVaccination(@RequestBody VaccineRegistrationRequest request) {
            Appointment appointment = appointmentService.createAppointment(request);
            ApiResponse<Appointment> response = new ApiResponse<>("200", appointment, "Appointment created successfully");
            return ResponseEntity.ok(response);
    }

}
