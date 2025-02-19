package com.fpt.swd392.cvsts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.entities.VaccinationRecord;
import com.fpt.swd392.cvsts.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/vac-record")
    public ResponseEntity<?> createVaccinationRecord(@RequestBody VaccinationRecord record) {
        try {
            VaccinationRecord createdRecord = userService.createVaccinationRecord(record);
            return ResponseEntity.ok(createdRecord);
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .body("Error creating vaccination record: " + e.getMessage());
        }
    }

    @GetMapping("/vac-record")
    public ResponseEntity<?> getChildProfileDetails(@RequestParam String id) {
        return ResponseEntity.ok(userService.getVaccinationRecordDetail(id));
    }

    @GetMapping("/vac-records")
    public ResponseEntity<?> getChildProfiles(@RequestParam String userId) {
        return ResponseEntity.ok(userService.getVaccinationRecordsByUserId(userId));
    }

    @PatchMapping("/vac-record")
    public ResponseEntity<?> updateVaccinationRecord(@RequestBody VaccinationRecord record) {
        try {
            if (record.getId() == null || record.getId().isEmpty()) {
                return ResponseEntity
                    .badRequest()
                    .body("Vaccination record ID is required for update");
            }
            
            VaccinationRecord updatedRecord = userService.updateVaccinationRecord(record);
            return ResponseEntity.ok(updatedRecord);
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .body("Error updating vaccination record: " + e.getMessage());
        }
    }

    @DeleteMapping("/vac-record")
    public ResponseEntity<?> deleteVaccinationRecord(@RequestParam String id) {
        try {
            userService.deleteVaccinationRecord(id);
            return ResponseEntity.ok("Vaccination record deleted successfully");
        } catch (Exception e) {
            return ResponseEntity
                .badRequest()
                .body("Error deleting vaccination record: " + e.getMessage());
        }
    }

}