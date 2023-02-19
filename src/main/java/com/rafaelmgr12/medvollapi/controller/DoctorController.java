package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.dto.ListDataDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.RegisterDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.UpdataeDoctorsDTO;
import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody RegisterDoctorDTO dto) {
        repository.save(new Doctor(dto));
    }

    @GetMapping
    public Page<ListDataDoctorDTO> list(@PageableDefault(sort = {"name"}) Pageable pageable) {
        return repository.findAll(pageable).map(ListDataDoctorDTO::new);

    }
    @PutMapping
    @Transactional
    public void update(@RequestBody UpdataeDoctorsDTO dto) {
        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.updateData(dto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();
    }

}
