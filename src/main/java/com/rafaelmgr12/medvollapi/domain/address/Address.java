package com.rafaelmgr12.medvollapi.domain.address;

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

    public void updateData(AddressDTO address) {
        if (address.street() != null) {
            this.street = address.street();
        }
        if (address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }
        if (address.number() != null) {
            this.number = address.number();
        }
        if (address.complement() != null) {
            this.complement = address.complement();
        }
        if (address.zipcode() != null) {
            this.zipcode = address.zipcode();
        }
        if (address.city() != null) {
            this.city = address.city();
        }
        if (address.state() != null) {
            this.state = address.state();
        }

    }
}
