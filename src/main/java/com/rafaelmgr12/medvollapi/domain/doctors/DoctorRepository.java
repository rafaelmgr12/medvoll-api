package com.rafaelmgr12.medvollapi.domain.doctors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.Optional;


public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pageable);

    Optional<Doctor> findByIdAndActiveIsTrue(Long aLong);

    @Query("""
        select d from Doctor d
        where
        d.active = true
        and
        d.speciality = :speciality
        and
        d.id not in(
        select a.doctor.id from Appointment a
        where
        a.date = :date
        )
        order by rand()
        limit 1
        """)
    Doctor chooseRandomFreeDoctorOnFreeDate(Speciality speciality, LocalDateTime date);

    @Query("""
        select d.active from Doctor d
        where
        d.id = :doctorId
        """)
    Boolean findActiveById(Long doctorId);
}
