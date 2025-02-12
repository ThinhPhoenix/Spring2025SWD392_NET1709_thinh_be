package com.fpt.swd392.cvsts.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")

public class User {
    @Id
    private String id;

    @Column(name = "manager_id")
    private String managerID;

    @Column(name = "banned_by")
    private String bannedBy;
    
    private String fullname;
    private String role;
    private Character gender;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    private String email;
    private String password;
    private LocalDate birthday;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;
    
    @Column(name = "banned_at")
    private LocalDateTime bannedAt;

}
