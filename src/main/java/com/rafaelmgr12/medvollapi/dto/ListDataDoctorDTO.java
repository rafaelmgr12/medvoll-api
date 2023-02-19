package com.rafaelmgr12.medvollapi.dto;

import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.entity.Speciality;

public record ListDataDoctorDTO(
        String name,
        String email,
        String crm,
        Speciality speciality
) {
    public ListDataDoctorDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
