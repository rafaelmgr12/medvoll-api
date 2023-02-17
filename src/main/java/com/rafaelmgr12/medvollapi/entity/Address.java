package com.rafaelmgr12.medvollapi.entity;

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
    private String cep;
    private String city;
    private String state;

}
