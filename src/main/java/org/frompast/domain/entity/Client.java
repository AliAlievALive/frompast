package org.frompast.domain.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;
}