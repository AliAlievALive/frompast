package org.frompast.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User extends People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String distinguishedName;

    String commonName;

    String canonicalName;

    String guid;

    String userPrincipalName;

    String displayName;

    String otherName;

    String initials;

    String telephoneNumber;

    String homePhone;

    String company;

    String organization;

    String division;

    String department;

    String office;

    String manager;

    String employeeId;

    String employeeNumber;

    String mailNickname;

    String samAccountName;

    String officePhone;

    String ipPhone;

    String title;

    String userAccountControl;

    String enabled;

    @NotNull
    @Column(nullable = false)
    @Builder.Default
    Boolean isActive = true;

    @NotNull
    @Column(nullable = false)
    @UpdateTimestamp
    LocalDateTime syncDate;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    Set<File> files = new HashSet<>();

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true)
    Set<Client> clients = new HashSet<>();

}


