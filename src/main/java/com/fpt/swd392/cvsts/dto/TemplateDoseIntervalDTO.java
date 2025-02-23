package com.fpt.swd392.cvsts.dto;

import lombok.Data;

@Data
public class TemplateDoseIntervalDTO {
    private String fromVaccineId;
    private int fromDoseNumber;
    private String toVaccineId;
    private int toDoseNumber;
    private int daysBetween;
}
