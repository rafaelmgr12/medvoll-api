package com.rafaelmgr12.medvollapi.domain.doctors;

import com.rafaelmgr12.medvollapi.domain.address.Address;

public record DetailsDoctorDTO(
        Long id,
        String name,
        String email,
        String phone,
        Speciality speciality,
        Address address) {
    public DetailsDoctorDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(), doctor.getSpeciality(), doctor.getAddress());
    }
}
