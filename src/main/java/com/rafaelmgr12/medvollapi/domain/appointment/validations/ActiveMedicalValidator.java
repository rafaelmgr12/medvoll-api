package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.domain.doctors.DoctorRepository;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActiveMedicalValidator implements AppointmentSchedulingValidator{

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        if (data.doctorId() == null){
            return;
        }

        Boolean doctorActive = doctorRepository.findActiveById(data.doctorId());
        if (Boolean.FALSE.equals(doctorActive)){
            throw new ValidationException("Consulta não pode ser agendada com médico excluído");
        }

    }
}
