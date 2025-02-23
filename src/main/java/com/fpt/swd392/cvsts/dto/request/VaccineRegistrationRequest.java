package com.fpt.swd392.cvsts.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class VaccineRegistrationRequest {
    private String vaccinationRecordId;
    private String vaccineId;
    private LocalDate scheduleDate;
    private String vaccinePackageId;
    private LocalTime timeFrom;
    private String timeTo;
    private long totalPrice;
    private String notes;


}
  