package com.fpt.swd392.cvsts.services;

import java.util.List;
import com.fpt.swd392.cvsts.dto.request.SignupRequest;
import com.fpt.swd392.cvsts.dto.response.UserResponse;
import com.fpt.swd392.cvsts.entities.User;
import com.fpt.swd392.cvsts.entities.VaccinationRecord;

public interface IUserService {
    User registerUser(SignupRequest request);
    User getUserById(String id);
    VaccinationRecord getVaccinationRecordDetail(String recordId);
    List<VaccinationRecord> getVaccinationRecordsByUserId(String userId);
    VaccinationRecord createVaccinationRecord(VaccinationRecord record);
    VaccinationRecord updateVaccinationRecord(VaccinationRecord record);
    void deleteVaccinationRecord(String recordId);
    UserResponse getUserInfo(String id);
}