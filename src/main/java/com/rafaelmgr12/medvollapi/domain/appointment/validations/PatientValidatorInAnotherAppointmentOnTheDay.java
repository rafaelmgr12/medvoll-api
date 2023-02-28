package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.AppointmentRepository;
import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientValidatorInAnotherAppointmentOnTheDay implements AppointmentSchedulingValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        LocalDateTime firstHour = data.date().withHour(7);
        LocalDateTime lastHour = data.date().withHour(18);
        Boolean patientHasOtherAppointmentOnTheDay = appointmentRepository.existsByPatientIdAndDateBetween(data.patientId(), firstHour, lastHour);
        if (Boolean.TRUE.equals(patientHasOtherAppointmentOnTheDay)) {
            throw new ValidationException("Paciente já possui consulta agendada para o dia");
        }
    }
}
