package com.rafaelmgr12.medvollapi.entity;


import com.rafaelmgr12.medvollapi.dto.RegisterPatientDTO;
import com.rafaelmgr12.medvollapi.dto.UpdatePatientDTO;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "patients")
@Entity(name = "Patient")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String phone;
    private String cpf;

    @Embedded
    private Address address;
    private Boolean active;

    public Patient(RegisterPatientDTO dto) {
        this.active = true;
        this.name = dto.name();
        this.email = dto.email();
        this.phone = dto.phone();
        this.cpf = dto.cpf();
        this.address = new Address(dto.address());
    }

    public void  updateData(UpdatePatientDTO dto) {
        if (dto.name() != null) {
            this.name = dto.name();
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
