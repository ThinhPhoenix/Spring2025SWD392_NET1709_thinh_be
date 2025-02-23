package com.fpt.swd392.cvsts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fpt.swd392.cvsts.entities.Blog;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;
import com.fpt.swd392.cvsts.services.IBlogService;
import com.fpt.swd392.cvsts.services.UserService;
import com.fpt.swd392.cvsts.utils.ApiResponse;
import java.util.List;
import com.fpt.swd392.cvsts.dto.VaccinationAppointmentDTO;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;
    private IBlogService blogService;

    public UserController(UserService userService, IBlogService blogService) {
        this.userService = userService;
        this.blogService = blogService;
    }

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

    @GetMapping("/blogs")
    public ResponseEntity<?> getAllBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Pageable pageable = PageRequest.of(
            page,
            size,
            Sort.by(Sort.Direction.fromString(direction), sort)
        );
        Page<Blog> blogs = blogService.getAllBlogs(pageable);
        return ResponseEntity.ok(blogs);
    }

    @GetMapping("/blog")
    public ResponseEntity<?> getBlogById(@RequestParam String blogId) {
        return ResponseEntity.ok(blogService.getBlogById(blogId));
    }

    @GetMapping("/schedule/{id}")
    public ResponseEntity<ApiResponse<List<VaccinationAppointmentDTO>>> getCustomerVaccinationSchedule(@PathVariable String id) {
        List<VaccinationAppointmentDTO> results = userService.getCustomerVaccinationSchedule(id);
        ApiResponse<List<VaccinationAppointmentDTO>> response = new ApiResponse<>("200", results, "Appointment schedule retrieved successfully");
        return ResponseEntity.ok(response);
    }
}