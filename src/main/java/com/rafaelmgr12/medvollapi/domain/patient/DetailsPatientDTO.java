package com.rafaelmgr12.medvollapi.domain.patient;

import com.rafaelmgr12.medvollapi.domain.address.Address;

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
