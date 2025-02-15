package com.fpt.swd392.cvsts.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddressDTO {
    @NotBlank(message = "unitNumber is required")
    private String unitNumber;

    @NotNull(message = "ward is required")
    private String ward;

    @NotNull(message = "district is required")
    private String district;

    @NotNull(message = "province is required")
    private String province;
}
