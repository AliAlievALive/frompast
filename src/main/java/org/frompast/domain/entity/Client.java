package org.frompast.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Client extends People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
}