package com.fpt.swd392.cvsts.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccination_records")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VaccinationRecord {
    @Id
    private String id;
    
    @Column(name = "customer_id")
    private String customerId;
    
    @Column(name = "child_name")
    private String childName;
    
    @Column(name = "child_gender")
    private String childGender;
    
    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;
    
    @Column(name = "parent_name")
    private String parentName;
    
    @Column(name = "birth_place")
    private String birthPlace;
    
    @Column(name = "gestation_age")
    private Integer gestationAge;
    
    @Column(name = "birth_method")
    private String birthMethod;
    
    @Column(name = "birth_weight")
    private Float birthWeight;
    
    @Column(name = "birth_height")
    private Integer birthHeight;
    
    private String abnormalities;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
