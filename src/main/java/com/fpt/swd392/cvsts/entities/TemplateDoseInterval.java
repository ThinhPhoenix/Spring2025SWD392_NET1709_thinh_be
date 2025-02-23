package com.fpt.swd392.cvsts.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "template_dose_intervals")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDoseInterval {
    @Id
    private String id;

    @Column(name = "template_id")
    private String templateId;

    @Column(name = "from_vaccine_id")
    private String fromVaccineId;

    @Column(name = "from_dose_number")
    private int fromDoseNumber;

    @Column(name = "to_vaccine_id")
    private String toVaccineId;

    @Column(name = "to_dose_number")
    private int toDoseNumber;

    @Column(name = "days_between")
    private int daysBetween;
}
