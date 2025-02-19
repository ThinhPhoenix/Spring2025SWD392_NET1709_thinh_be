package com.fpt.swd392.cvsts.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class VaccineServiceDTO {
    private String vaccinePackageId;
    private String vaccinePackageName;
    private String description;
    private int totalPrice;
    private VaccineLineServiceDTO vaccineLineServiceDTO;
    private int toltalVaccinePrice;
}

