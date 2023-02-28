package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AdvanceTimeValidator implements AppointmentSchedulingValidator{

    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        LocalDateTime appointmentDate = data.date();
        LocalDateTime now = LocalDateTime.now();
        Long differenceBetweenDates = Duration.between(now, appointmentDate).toMinutes();

        if (differenceBetweenDates < 30) {
            throw new ValidationException("Consulta deve ser agendada com antecedência mínima de 30 minutos");
        }

    }
}
