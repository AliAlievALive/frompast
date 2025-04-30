package org.frompast.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_msg")
public class Message {
    @Id
    Long id;

    @Column(name = "msg_txt")
    String text;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "message", orphanRemoval = true)
    Set<File> files = new HashSet<>();
}
