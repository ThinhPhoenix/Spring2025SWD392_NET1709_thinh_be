package com.fpt.swd392.cvsts.services;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fpt.swd392.cvsts.dto.AddressDTO;
import com.fpt.swd392.cvsts.entities.Address;
import com.fpt.swd392.cvsts.repositories.AddressRepository;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(AddressDTO addressDTO, String userId) {
        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setUserId(userId);
        address.setUnitNumber(addressDTO.getUnitNumber());
        address.setWard(addressDTO.getWard());
        address.setDistrict(addressDTO.getDistrict());
        address.setProvince(addressDTO.getProvince());
        address.setCreatedAt(LocalDateTime.now());
        
        return addressRepository.save(address);
    }
}
