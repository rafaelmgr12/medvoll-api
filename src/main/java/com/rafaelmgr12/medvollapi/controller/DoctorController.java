package com.rafaelmgr12.medvollapi.controller;

import com.rafaelmgr12.medvollapi.domain.doctors.DetailsDoctorDTO;
import com.rafaelmgr12.medvollapi.domain.doctors.ListDataDoctorDTO;
import com.rafaelmgr12.medvollapi.domain.doctors.RegisterDoctorDTO;
import com.rafaelmgr12.medvollapi.domain.doctors.UpdataeDoctorsDTO;
import com.rafaelmgr12.medvollapi.domain.doctors.Doctor;
import com.rafaelmgr12.medvollapi.domain.doctors.DoctorRepository;
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
    public ResponseEntity<DetailsDoctorDTO> register(@RequestBody RegisterDoctorDTO dto, UriComponentsBuilder uriBuilder) {
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
    public ResponseEntity<DetailsDoctorDTO> update(@RequestBody UpdataeDoctorsDTO dto) {
        Doctor doctor = repository.getReferenceById(dto.id());
        doctor.updateData(dto);

        return ResponseEntity.ok(new DetailsDoctorDTO(doctor));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        Doctor doctor = repository.getReferenceById(id);
        doctor.delete();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetailsDoctorDTO> detail(@PathVariable Long id){
        Doctor doc = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailsDoctorDTO(doc));

    }

}
