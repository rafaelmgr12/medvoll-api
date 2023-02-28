package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.domain.patient.PatientRepository;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActivePatientValidator implements AppointmentSchedulingValidator{

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        Boolean isActive = patientRepository.findActiveById(data.patientId());
        if (Boolean.FALSE.equals(isActive)) {
            throw new ValidationException("Consulta não pode ser agendada com paciente excluído");
        }


    }
}
