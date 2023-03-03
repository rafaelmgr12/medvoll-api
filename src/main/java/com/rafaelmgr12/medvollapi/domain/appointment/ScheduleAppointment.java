package com.rafaelmgr12.medvollapi.domain.appointment;

import com.rafaelmgr12.medvollapi.domain.appointment.validations.AppointmentSchedulingValidator;
import com.rafaelmgr12.medvollapi.domain.doctors.Doctor;
import com.rafaelmgr12.medvollapi.domain.doctors.DoctorRepository;
import com.rafaelmgr12.medvollapi.domain.patient.Patient;
import com.rafaelmgr12.medvollapi.domain.patient.PatientRepository;
import com.rafaelmgr12.medvollapi.infra.execption.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ScheduleAppointment {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private List<AppointmentSchedulingValidator> validators;

    public AppointmentDetailDTO schedule(DataSchedulingConsultationDTO data){
        if (!patientRepository.existsById(data.patientId())){
            throw new ValidationException("Paciente não encontrado");}
        if (data.doctorId() != null && !doctorRepository.existsById(data.doctorId())){
            throw new ValidationException("Médico não encontrado");}

        validators.forEach(validator -> validator.validate(data));

        Patient patient = patientRepository.getReferenceById(data.patientId());
        Doctor doctor = chooseDoctor(data);
        if (doctor == null){
            throw new ValidationException("Não há médicos disponíveis para a especialidade informada nesta data");
        }

        Appointment appointment = new Appointment(null, doctor, patient, data.date());
        appointmentRepository.save(appointment);

        return new AppointmentDetailDTO(appointment);
    }

    private Doctor chooseDoctor (DataSchedulingConsultationDTO data){
        if (data.doctorId() != null){
            return doctorRepository.getReferenceById(data.doctorId());
        }
        if (data.speciality() == null) {
            throw new ValidationException("Especialidade não informada");
        }

        return doctorRepository.chooseRandomFreeDoctorOnFreeDate(data.speciality(),data.date());
    }

}
