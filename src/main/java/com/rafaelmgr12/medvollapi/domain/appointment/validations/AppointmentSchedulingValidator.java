package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;

public interface AppointmentSchedulingValidator {

    void validate(DataSchedulingConsultationDTO data);
}
