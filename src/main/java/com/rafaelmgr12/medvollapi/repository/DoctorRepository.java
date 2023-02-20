package com.rafaelmgr12.medvollapi.repository;

import com.rafaelmgr12.medvollapi.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    Optional<Doctor> findByIdAndActiveIsTrue(Long aLong);
}
