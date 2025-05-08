package org.frompast.domain.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public abstract class People {
    String fullName;
    String firstname;
    String lastname;
    String mobilePhone;
    String country;
    String state;
    String city;
    String street;
    String postalCode;
    String mail;
}


