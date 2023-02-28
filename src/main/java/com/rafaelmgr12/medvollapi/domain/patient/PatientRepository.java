package com.rafaelmgr12.medvollapi.domain.patient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Page<Patient> findAllByActiveTrue(Pageable pageable);

    @Query("""
        select p.active from Patient p where p.id = :patientId
""")
    Boolean findActiveById(Long patientId);
}
