package com.rafaelmgr12.medvollapi.domain.patient;

import com.rafaelmgr12.medvollapi.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record UpdatePatientDTO(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressDTO address) {}