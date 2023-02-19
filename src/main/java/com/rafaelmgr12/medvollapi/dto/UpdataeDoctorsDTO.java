package com.rafaelmgr12.medvollapi.dto;

import jakarta.validation.constraints.NotNull;

public record UpdataeDoctorsDTO(
        @NotNull
        Long id,
        String name,
        String email,
        String phone,
        AddressDTO address) {}
