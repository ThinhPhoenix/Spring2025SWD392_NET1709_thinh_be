package com.fpt.swd392.cvsts.entities;

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
@Table(name = "post_vaccination_followups")
public class PostVaccinationFollowup {
    @Id
    private String id;

    @Column(name = "vaccination_detail_id")
    private String vaccinationDetailId;

    @Column(name = "checkup_time")
    private LocalDateTime checkupTime;

    @Column(name = "status")
    private String status;

    @Column(name = "abnormalities")
    private String abnormalities;

    @Column(name = "temperature")
    private Integer temperature; 

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
