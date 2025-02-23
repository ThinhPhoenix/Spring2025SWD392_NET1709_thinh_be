package com.fpt.swd392.cvsts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
    @Id
    private String id;

    @Column(name = "vaccine_package_id")
    private String vaccinePackageId;

    @Column(name = "notes")
    private String notes;

    @Column(name = "total_price")
    private Long totalPrice;

    @Column(name = "vaccination_record_id")
    private String vaccinationRecordId;

}