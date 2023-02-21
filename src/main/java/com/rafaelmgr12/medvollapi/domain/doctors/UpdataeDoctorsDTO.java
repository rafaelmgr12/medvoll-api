package com.rafaelmgr12.medvollapi.domain.doctors;

import com.rafaelmgr12.medvollapi.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record UpdataeDoctorsDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO address) {}
