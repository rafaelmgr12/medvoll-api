package com.rafaelmgr12.medvollapi.repository;

import com.rafaelmgr12.medvollapi.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

}
