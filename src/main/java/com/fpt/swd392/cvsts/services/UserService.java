package com.fpt.swd392.cvsts.services;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fpt.swd392.cvsts.dto.AddressDTO;
import com.fpt.swd392.cvsts.dto.request.SignupRequest;
import com.fpt.swd392.cvsts.dto.response.UserResponse;
import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;
import com.fpt.swd392.cvsts.repositories.UserRepository;
import com.fpt.swd392.cvsts.repositories.VaccinationRepository;

@Service
public class UserService implements IUserService {
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

    public UserResponse getUserInfo(String id) {
        return userRepository.getUserInfo(id)
        .map(outerRow -> {
                UserResponse dto = new UserResponse();
                Object[] row = (Object[]) outerRow[0];
                AddressDTO address = new AddressDTO();
                System.out.println("length >>>>>>: " + row.length);
                for (int i = 0; i < row.length; i++) {
                    System.out.println("Index " + i + ": " + row[i]); // Kiểm tra giá trị từng phần tử
                }
                dto.setId(row[0].toString());
                dto.setFullname(row[1].toString());
                dto.setEmail(row[2].toString());
                dto.setPhoneNumber(row[3].toString());
                dto.setGender(row[4].toString());
                address.setUnitNumber(row[5] != null ? row[5].toString() : null);
                address.setWard(row[6] != null ? row[6].toString() : null);
                address.setDistrict(row[7] != null ? row[7].toString() : null);
                address.setProvince(row[8] != null ? row[8].toString() : null);
                dto.setAddress(address);
                dto.setRole(row[9].toString());
                if (row[10] != null) {
                    java.sql.Date sqlDate = (java.sql.Date) row[10];
                    dto.setBirthday(sqlDate.toLocalDate());
                }
                if (row[11] != null) {
                    java.sql.Timestamp timestamp = (java.sql.Timestamp) row[11];
                    dto.setCreatedAt(timestamp.toLocalDateTime());
                }
                return dto;
            })
        .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
