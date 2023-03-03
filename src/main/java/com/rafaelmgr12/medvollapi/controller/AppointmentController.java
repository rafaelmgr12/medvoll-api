package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.domain.appointment.AppointmentDetailDTO;
import com.rafaelmgr12.medvollapi.domain.appointment.DataSchedulingConsultationDTO;
import com.rafaelmgr12.medvollapi.domain.appointment.ScheduleAppointment;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class AppointmentController {

    @Autowired
    private ScheduleAppointment scheduleAppointment;
    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDetailDTO> schedule(@RequestBody @Valid DataSchedulingConsultationDTO data){
        AppointmentDetailDTO dto = scheduleAppointment.schedule(data);
        return ResponseEntity.ok(dto);
    }
}
