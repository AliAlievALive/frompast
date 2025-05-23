package org.frompast.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.frompast.web.dto.file.FileReadDto;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "user")
public class UserReadDto {

    Long id;
    String distinguishedName;
    String commonName;
    String canonicalName;
    String guid;
    String userPrincipalName;
    String displayName;
    String fullName;
    String firstname;
    String lastname;
    String otherName;
    String initials;
    String telephoneNumber;
    String homePhone;
    String mobilePhone;
    String country;
    String state;
    String city;
    String street;
    String postalCode;
    String company;
    String organization;
    String division;
    String department;
    String office;
    String manager;
    String employeeId;
    String employeeNumber;
    String mail;
    String mailNickname;
    String samAccountName;
    String officePhone;
    String ipPhone;
    String title;
    String userAccountControl;
    String enabled;
    Boolean isActive;
    LocalDateTime syncDate;
    List<FileReadDto> files;
}
