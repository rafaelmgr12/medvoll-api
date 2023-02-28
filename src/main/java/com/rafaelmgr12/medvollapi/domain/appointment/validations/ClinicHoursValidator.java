package com.rafaelmgr12.medvollapi.domain.appointment.validations;

import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ClinicHoursValidator implements AppointmentSchedulingValidator{
    @Override
    public void validate(DataSchedulingConsultationDTO data) {
        LocalDateTime appointmentDate = data.date();

        boolean sunday = appointmentDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        boolean beforeClinicOpens = appointmentDate.getHour() < 7;
        boolean afterClinicCloses = appointmentDate.getHour() > 18;

        if (sunday || beforeClinicOpens ||afterClinicCloses) {
            throw new ValidationException("A clínica não atende neste horário");
        }
    }
}
