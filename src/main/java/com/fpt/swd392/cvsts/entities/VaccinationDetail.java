package com.fpt.swd392.cvsts.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vaccination_details")
public class VaccinationDetail {
    @Id
    private String id;

    @Column(name = "vaccination_record_id")
    private String vaccinationRecordId;

    @Column(name = "vaccine_batch_id")
    private String vaccineBatchId;

    @Column(name = "disease_type_id")
    private String diseaseTypeId;

    @Column(name = "vaccinator_id")
    private String vaccinatorId;

    @Column(name = "sequence_name")
    private String sequenceName;

    @Column(name = "vaccination_date")
    private LocalDate vaccinationDate;

    @Column(name = "physician_name")
    private String physicianName;

    @Column(name = "injection_route")
    private String injectionRoute;

    @Column(name = "injection_site")    
    private String injectionSite;

    @Column(name = "vaccinator_name")
    private String vaccinatorName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "notes")
    private String notes;
}