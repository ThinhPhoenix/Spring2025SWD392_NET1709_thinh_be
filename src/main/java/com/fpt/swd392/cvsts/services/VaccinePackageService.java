package com.fpt.swd392.cvsts.services;

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
import jakarta.transaction.Transactional;

@Service
@Transactional
public class VaccinePackageService {
    @Autowired
    private VaccinePackageRepository vaccinePackageRepository;

    public Page<VaccineServiceDTO> getAllVaccinePackageDetails(Pageable pageable) {
        List<Object[]> results = vaccinePackageRepository.getAllVaccinePackagesDetails(pageable.getPageSize(), (int) pageable.getOffset());
        long total = vaccinePackageRepository.countVacinePackageDetails();    
        List<VaccineServiceDTO> dtos = results.stream().map(vpd -> {
            VaccineServiceDTO vaccinePackageDetail = new VaccineServiceDTO();
            vaccinePackageDetail.setVaccinePackageId(vpd[0].toString());
            vaccinePackageDetail.setVaccinePackageName(vpd[1].toString());
            vaccinePackageDetail.setDescription(vpd[2].toString());
            vaccinePackageDetail.setTotalPrice(Integer.parseInt(vpd[3].toString()));
            vaccinePackageDetail.setToltalVaccinePrice(Integer.parseInt(vpd[7].toString()));
            return vaccinePackageDetail;
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

        

        return new PageImpl<>(dtos, pageable, total);
    }
}
