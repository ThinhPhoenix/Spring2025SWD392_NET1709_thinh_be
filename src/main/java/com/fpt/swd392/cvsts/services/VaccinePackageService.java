package com.fpt.swd392.cvsts.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fpt.swd392.cvsts.dto.response.VaccineLineServiceDTO;
import com.fpt.swd392.cvsts.dto.response.VaccineServiceDTO;
import com.fpt.swd392.cvsts.entities.VaccinePackage;
import com.fpt.swd392.cvsts.repositories.VaccinePackageRepository;
import com.fpt.swd392.cvsts.utils.ApiResponse;
import com.fpt.swd392.cvsts.utils.ListPageable;
import com.fpt.swd392.cvsts.utils.PageBound;
import com.fpt.swd392.cvsts.utils.Paging;
import com.fpt.swd392.cvsts.utils.Utils;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class VaccinePackageService {
    @Autowired
    private VaccinePackageRepository vaccinePackageRepository;

    public ApiResponse<List<VaccineServiceDTO>> getAllVaccinePackageDetails(int page , int size) {
        try {
            List<Object[]> results = vaccinePackageRepository.getAllVaccinePackagesDetails();    
        List<VaccineServiceDTO> dtos = results.stream().map(vpd -> {
            VaccineServiceDTO vaccinePackage = new VaccineServiceDTO();
            vaccinePackage.setVaccinePackageId(vpd[0].toString());
            vaccinePackage.setVaccinePackageName(vpd[1].toString());
            vaccinePackage.setDescription(vpd[2].toString());
            vaccinePackage.setTotalPrice(Integer.parseInt(vpd[3].toString()));
            vaccinePackage.setToltalVaccinePrice(Integer.parseInt(vpd[8].toString()));
            return vaccinePackage;
        }).collect(Collectors.toList());
        Set<VaccineServiceDTO> vaccinePackageDetails = new HashSet<>(dtos);
        
        List<VaccineLineServiceDTO> vaccineLineServiceDTOs = results.stream().map(vpd -> {
            VaccineLineServiceDTO vaccineLineServiceDTO = new VaccineLineServiceDTO();
            vaccineLineServiceDTO.setVaccinePackageId(vpd[0].toString());
            vaccineLineServiceDTO.setDiseaseName(vpd[4].toString());
            vaccineLineServiceDTO.setVaccineName(vpd[5].toString());
            vaccineLineServiceDTO.setCountryOfOrigin(vpd[6].toString());
            vaccineLineServiceDTO.setDoseNumber(Integer.parseInt(vpd[7].toString()));
            return vaccineLineServiceDTO;
        }).collect(Collectors.toList());
        createVaccinePackageDetail(vaccinePackageDetails, vaccineLineServiceDTOs);

        
        List<VaccineServiceDTO> list = new ArrayList<>(vaccinePackageDetails);
        PageBound pageBound = Utils.calculatePageBounds(page, size, list.size());
        List<VaccineServiceDTO> paginatedList = list.subList(pageBound.getStartIndex(), pageBound.getEndIndex());

        Paging paging = new Paging(page, size, Utils.calculateTotalPage(list.size(), size));
        return new ApiResponse<>("200", paginatedList, "Get all vaccine packages successfully", paging);
        
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public void createVaccinePackageDetail (Set<VaccineServiceDTO> vaccinePackages, List<VaccineLineServiceDTO> vaccineLineServiceDTOs){
        for (VaccineServiceDTO vaccinePackage : vaccinePackages) {
            for (VaccineLineServiceDTO vaccineLineServiceDTO : vaccineLineServiceDTOs) {
                if (vaccinePackage.getVaccinePackageId().equals(vaccineLineServiceDTO.getVaccinePackageId())) {
                    vaccinePackage.getVaccineLineServiceDTO().add(vaccineLineServiceDTO);
                }
            }
        }
    }
}
