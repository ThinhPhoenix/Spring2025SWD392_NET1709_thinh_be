package com.fpt.swd392.cvsts.dto.response;

import java.util.List;

import com.fpt.swd392.cvsts.entities.Vaccine;
import com.fpt.swd392.cvsts.entities.VaccinePackage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class VaccineBasicInfoDTO {
    private List<Vaccine> vaccines;
    private List<VaccinePackage> vaccinePackages;
}
