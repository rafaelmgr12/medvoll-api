package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.dto.ListDataDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.RegisterDoctorDTO;
import com.rafaelmgr12.medvollapi.dto.UpdataeDoctorsDTO;
import com.rafaelmgr12.medvollapi.dto.DetailsDoctorDTO;
import com.rafaelmgr12.medvollapi.entity.Doctor;
import com.rafaelmgr12.medvollapi.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody RegisterDoctorDTO dto, UriComponentsBuilder uriBuilder) {
        Doctor doc = new Doctor(dto);
        repository.save(doc);

        URI uri = uriBuilder.path("/medicos/{id}").buildAndExpand(doc.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailsDoctorDTO(doc));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataDoctorDTO>> list(@PageableDefault(sort = {"name"}) Pageable pageable) {
        Page<ListDataDoctorDTO> page =  repository.findAllByActiveTrue(pageable).map(ListDataDoctorDTO::new);
        return ResponseEntity.ok(page);

    }
    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody UpdataeDoctorsDTO dto) {
        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.updateData(dto);

        return ResponseEntity.ok(new DetailsDoctorDTO(doctor));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

}
