package com.fpt.swd392.cvsts.services;

import java.util.List;

import com.fpt.swd392.cvsts.dto.response.VaccineBasicInfoDTO;
import com.fpt.swd392.cvsts.dto.response.VaccineServiceDTO;
import com.fpt.swd392.cvsts.utils.ApiResponse;

public interface IVacccinePackageService {
    ApiResponse<List<VaccineServiceDTO>> getAllVaccinePackageDetails(int page , int size);
    public VaccineBasicInfoDTO getVaccineBasicInfos();

}
