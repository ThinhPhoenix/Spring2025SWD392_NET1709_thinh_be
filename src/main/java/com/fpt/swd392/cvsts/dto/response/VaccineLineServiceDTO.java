package com.fpt.swd392.cvsts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VaccineLineServiceDTO {
    private String vaccinePackageId;
    private String diseaseName;
    private String vaccineName;
    private String countryOfOrigin;
    private int doseNumber;
}
