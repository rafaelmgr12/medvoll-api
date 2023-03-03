package com.rafaelmgr12.medvollapi.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
    public AppointmentDetailDTO(Appointment appointment) {
        this(
                appointment.getId(),
                appointment.getDoctor().getId(),
                appointment.getPatient().getId(),
                appointment.getDate()
        );
    }
}
