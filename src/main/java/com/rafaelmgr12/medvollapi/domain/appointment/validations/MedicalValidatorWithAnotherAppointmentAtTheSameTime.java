package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.AppointmentRepository;
import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicalValidatorWithAnotherAppointmentAtTheSameTime implements AppointmentSchedulingValidator{

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        boolean doctorHasAnAppointmentAtTheSameTime = appointmentRepository
                .existsByDoctorIdAndDate(data.doctorId(), data.date());
        if (doctorHasAnAppointmentAtTheSameTime) {
            throw new ValidationException("O médico já tem uma consulta marcada neste horário");
        }
    }
}
