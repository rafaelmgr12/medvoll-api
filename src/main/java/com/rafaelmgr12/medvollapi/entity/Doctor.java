package com.rafaelmgr12.medvollapi.entity;

import com.rafaelmgr12.medvollapi.dto.RegisterDoctorDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(RegisterDoctorDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.speciality = dto.speciality();
        this.address = new Address(dto.address());
    }
}
