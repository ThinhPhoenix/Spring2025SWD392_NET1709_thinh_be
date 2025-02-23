package com.fpt.swd392.cvsts.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointment_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentDetail {
    @Id
    private String id;

    @Column(name = "vaccine_id")
    private String vaccineId;

    @Column(name = "vaccination_shift_id")
    private String vaccinationShiftId;

    @Column(name = "appointment_id")
    private String appointmentId;

    @Column(name = "type")
    private String type;

    @Column(name = "dose_number")
    private Integer doseNumber;

    @Column(name = "scheduled_date")
    private LocalDate scheduledDate;

    @Column(name = "actual_date")
    private LocalDate actualDate;

    @Column(name = "time_from")
    private LocalTime timeFrom;

    @Column(name = "time_to")
    private LocalTime timeTo;

    @Column(name = "status")
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public AppointmentDetail(String id, String vaccineId, String appointmentId, String type, Integer doseNumber,
    LocalDate scheduledDate, LocalTime timeFrom, String status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.vaccineId = vaccineId;
        this.appointmentId = appointmentId;
        this.type = type;
        this.doseNumber = doseNumber;
        this.scheduledDate = scheduledDate;
        this.timeFrom = timeFrom;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    
}