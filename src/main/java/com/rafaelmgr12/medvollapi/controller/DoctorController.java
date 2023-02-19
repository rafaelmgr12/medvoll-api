package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.dto.ListDataDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.RegisterDoctorDTO;
import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    public void register(@RequestBody RegisterDoctorDTO dto) {
        repository.save(new Doctor(dto));
    }

    @GetMapping
    public Page<ListDataDoctorDTO> list(@PageableDefault(size = 10,sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(ListDataDoctorDTO::new);

    }

}
