package com.rafaelmgr12.medvollapi.domain.doctors;

public record ListDataDoctorDTO(
        Long id,
        String name,
        String email,
        String crm,
        Speciality speciality
) {
    public ListDataDoctorDTO(Doctor doctor) {
        this(doctor.getId() ,doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpeciality());
    }
}
