package com.rafaelmgr12.medvollapi.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Boolean existsByDoctorIdAndDate(Long doctorId, LocalDateTime date);

    Boolean existsByPatientIdAndDateBetween(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);
}
