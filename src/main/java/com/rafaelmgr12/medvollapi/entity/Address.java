package com.rafaelmgr12.medvollapi.entity;

import com.rafaelmgr12.medvollapi.dto.AddressDTO;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String street;
    private String neighborhood;
    private String number;
    private String complement;
    private String zipcode;
    private String city;
    private String state;

    public Address(AddressDTO address) {
        this.street = address.street();
        this.neighborhood = address.neighborhood();
        this.number = address.number();
        this.complement = address.complement();
        this.zipcode = address.zipcode();
        this.city = address.city();
        this.state = address.state();
    }
}
