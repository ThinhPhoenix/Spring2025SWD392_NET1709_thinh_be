package com.fpt.swd392.cvsts.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.swd392.cvsts.dto.request.SignupRequest;
import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;
import com.fpt.swd392.cvsts.repositories.UserRepository;
import com.fpt.swd392.cvsts.repositories.VaccinationRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AddressService addressService;

    @Autowired
    private VaccinationRepository vaccinationRepository;

    @Transactional
    public User registerUser(SignupRequest request) {
        
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email is already taken!");
        }
        
        
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setFullname(request.getFullname());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setBirthday(request.getBirthday());
        user.setGender(request.getGender().charAt(0));
        user.setRole(request.getRole());
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        addressService.createAddress(request.getAddress(), savedUser.getId());
        
        return savedUser;
    }

    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public VaccinationRecord getVaccinationRecordDetail(String recordId) {
        return vaccinationRepository.findById(recordId).orElseThrow(() -> new RuntimeException("Record not found"));
    }

    public List<VaccinationRecord> getVaccinationRecordsByUserId(String userId) {
        return vaccinationRepository.findByCustomerId(userId);
    }

    public VaccinationRecord createVaccinationRecord(VaccinationRecord record) {
        record.setId(UUID.randomUUID().toString());
        record.setCreatedAt(LocalDateTime.now());
        return vaccinationRepository.save(record);
    }

    public VaccinationRecord updateVaccinationRecord(VaccinationRecord record) {
        // Check if record exists
        VaccinationRecord existingRecord = vaccinationRepository.findById(record.getId())
            .orElseThrow(() -> new RuntimeException("Vaccination record not found"));
        
        // Update mutable fields
        existingRecord.setChildName(record.getChildName());
        existingRecord.setChildGender(record.getChildGender());
        existingRecord.setDateOfBirth(record.getDateOfBirth());
        existingRecord.setParentName(record.getParentName());
        existingRecord.setBirthPlace(record.getBirthPlace());
        existingRecord.setGestationAge(record.getGestationAge());
        existingRecord.setBirthMethod(record.getBirthMethod());
        existingRecord.setBirthWeight(record.getBirthWeight());
        existingRecord.setBirthHeight(record.getBirthHeight());
        existingRecord.setAbnormalities(record.getAbnormalities());
        
        // Update timestamp
        existingRecord.setUpdatedAt(LocalDateTime.now());
        
        // Save and return
        return vaccinationRepository.save(existingRecord);
    }

    public void deleteVaccinationRecord(String recordId) {
        vaccinationRepository.deleteById(recordId);
    }
}
