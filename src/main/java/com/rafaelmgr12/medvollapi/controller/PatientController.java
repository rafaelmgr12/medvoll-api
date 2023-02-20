package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.dto.DetailsDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.DetailsPatientDTO;
import com.rafaelmgr12.medvollapi.dto.RegisterPatientDTO;
import com.rafaelmgr12.medvollapi.entity.Patient;
import com.rafaelmgr12.medvollapi.repository.PatientRepository;
import jakarta.transaction.Transactional;
import jdk.javadoc.doclet.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody RegisterPatientDTO dto, UriComponentsBuilder uriBuilder) {
        Patient patient = new Patient(dto);
        repository.save(patient);

        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsPatientDTO(patient));

    }

}
