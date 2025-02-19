package com.fpt.swd392.cvsts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "vaccines")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vaccine {
    @Id
    private String id;

    @Column(name = "vaccine_name")
    private String vaccineName;

    @Column(name = "vaccine_type")
    private String vaccineType;

    @Column(name = "manufacturer")
    private String manufacturer;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "dose_volume")
    private Float doseVolume;

    @Column(name = "doses_per_vial")
    private Integer dosesPerVial;

    @Column(name = "price_per_dose")
    private Integer pricePerDose;

    @Column(name = "disease_type_id")
    private String diseaseTypeId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

}
