package org.frompast.domain.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.ldap.odm.annotations.*;

import javax.naming.Name;

import static org.springframework.ldap.odm.annotations.Attribute.Type.BINARY;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entry(objectClasses = "user")
public class LdapEntity {

    @Id
    Name distinguishedName;

    @Transient
    @DnAttribute(value = "CN")
    String commonName;

    @Attribute(name = "CanonicalName")
    String canonicalName;

    @Attribute(name = "ObjectGUID", type = BINARY)
    byte[] guid;

    @Attribute(name = "UserPrincipalName")
    String userPrincipalName;

    @Attribute(name = "DisplayName")
    String displayName;

    @Attribute(name = "Name")
    String fullName;

    @Attribute(name = "GivenName")
    String firstname;

    @Attribute(name = "sn")
    String lastname;

    @Attribute(name = "OtherName")
    String otherName;

    @Attribute(name = "Initials")
    String initials;

    @Attribute(name = "telephoneNumber")
    String telephoneNumber;

    @Attribute(name = "HomePhone")
    String homePhone;

    @Attribute(name = "MobilePhone")
    String mobilePhone;

    @Attribute(name = "Country")
    String country;

    @Attribute(name = "State")
    String state;

    @Attribute(name = "City")
    String city;

    @Attribute(name = "street")
    String street;

    @Attribute(name = "PostalCode")
    String postalCode;

    @Attribute(name = "Company")
    String company;

    @Attribute(name = "Organization")
    String organization;

    @Attribute(name = "Division")
    String division;

    @Attribute(name = "Department")
    String department;

    @Attribute(name = "Office")
    String office;

    @Attribute(name = "Manager")
    String manager;

    @Attribute(name = "EmployeeID")
    String employeeId;

    @Attribute(name = "EmployeeNumber")
    String employeeNumber;

    @Attribute(name = "mail")
    String mail;

    @Attribute(name = "mailNickname")
    String mailNickname;

    @Attribute(name = "SamAccountName")
    String samAccountName;

    @Attribute(name = "OfficePhone")
    String officePhone;

    @Attribute(name = "ipPhone")
    String ipPhone;

    @Attribute(name = "Title")
    String title;

    @Attribute(name = "userAccountControl")
    String userAccountControl;

    @Attribute(name = "Enabled")
    String enabled;

    String password;
}
