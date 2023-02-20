package com.rafaelmgr12.medvollapi.dto;

import com.rafaelmgr12.medvollapi.entity.Address;
import com.rafaelmgr12.medvollapi.entity.Patient;

public record DetailsPatientDTO(
        Long id,
        String name,
        String email,
        String cpf,
        String phone,
        Address address
) {
    public DetailsPatientDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf(), patient.getPhone(), patient.getAddress());
    }
}
