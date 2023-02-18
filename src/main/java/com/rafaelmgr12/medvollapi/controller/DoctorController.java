package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.dto.RegisterDoctorDTO;
import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    public void register(@RequestBody RegisterDoctorDTO dto) {
        repository.save(new Doctor(dto));
    }

}
