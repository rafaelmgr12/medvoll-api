package com.rafaelmgr12.medvollapi.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDetailDTO(
        Long id,
        Long doctorId,
        Long patientId,
        LocalDateTime date
) {
}
