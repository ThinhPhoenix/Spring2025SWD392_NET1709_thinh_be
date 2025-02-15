package com.fpt.swd392.cvsts.entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "addresses")

public class Address {

    @Id
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "facility_id")
    private String facilityId;

    @Column(name = "unit_number")
    private String unitNumber;

    private String ward;
    private String district;
    private String province;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
}