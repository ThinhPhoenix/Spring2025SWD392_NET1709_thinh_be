package com.fpt.swd392.cvsts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vaccine_package_details")
@Data      
@AllArgsConstructor
@NoArgsConstructor
public class VaccinePackageDetails {

    @Id
    private String id;

    @Column(name = "vaccine_package_id")
    private String vaccinePackageId;

    @Column(name = "vaccine_id")
    private String vaccineId;

    @Column(name = "object_name")
    private String objectName;

    @Column(name = "dose_number")
    private int doseNumber;

   
}
