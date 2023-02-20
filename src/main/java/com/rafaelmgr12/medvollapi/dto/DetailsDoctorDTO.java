package com.rafaelmgr12.medvollapi.dto;

import com.rafaelmgr12.medvollapi.entity.Address;
import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.entity.Speciality;

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
