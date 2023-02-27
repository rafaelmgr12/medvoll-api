package com.rafaelmgr12.medvollapi.domain.appointment;

import com.rafaelmgr12.medvollapi.domain.doctors.Speciality;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DataSchedulingConsultationDTO(
        Long doctorId,
        @NotNull
        Long patientId,
        @NotNull
        @Future
        LocalDateTime date,

        Speciality speciality
) {
}
