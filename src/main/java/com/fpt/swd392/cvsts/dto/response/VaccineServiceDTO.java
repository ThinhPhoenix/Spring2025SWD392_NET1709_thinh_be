package com.fpt.swd392.cvsts.dto.response;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class VaccineServiceDTO {
    private String vaccinePackageId;
    private String vaccinePackageName;
    private String description;
    private int totalPrice;
    private List<VaccineLineServiceDTO> vaccineLineServiceDTO;
    private int toltalVaccinePrice;

    public VaccineServiceDTO() {
        vaccineLineServiceDTO = new ArrayList<>();
    }
}

