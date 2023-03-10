package com.rafaelmgr12.medvollapi.domain.doctors;

import com.rafaelmgr12.medvollapi.domain.address.Address;
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

    private Boolean active;

    @Enumerated(EnumType.STRING)
    private Speciality speciality;
    @Embedded
    private Address address;

    public Doctor(RegisterDoctorDTO dto) {
        this.active = true;
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.crm = dto.crm();
        this.speciality = dto.speciality();
        this.address = new Address(dto.address());
    }

    public void updateData(UpdataeDoctorsDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
        }
        if (dto.email() != null) {
            this.email = dto.email();
        }
        if (dto.phone() != null) {
            this.phone = dto.phone();
        }
        if (dto.address() != null) {
            this.address.updateData(dto.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
