package org.frompast.mapper;

import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.LdapEntity;
import org.mapstruct.Mapper;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.support.AbstractContextMapper;

@Slf4j
@Mapper
public abstract class LdapEntityMapper extends AbstractContextMapper<LdapEntity> {

    @Override
    public LdapEntity doMapFromContext(DirContextOperations ctx) {
        return LdapEntity.builder()
                .distinguishedName(ctx.getDn())
                .commonName(ctx.getStringAttribute("CN"))
                .canonicalName(ctx.getStringAttribute("CanonicalName"))
                .guid((byte[]) ctx.getObjectAttribute("ObjectGUID"))
                .userPrincipalName(ctx.getStringAttribute("UserPrincipalName"))
                .displayName(ctx.getStringAttribute("DisplayName"))
                .fullName(ctx.getStringAttribute("Name"))
                .firstname(ctx.getStringAttribute("GivenName"))
                .lastname(ctx.getStringAttribute("sn"))
                .otherName(ctx.getStringAttribute("OtherName"))
                .initials(ctx.getStringAttribute("Initials"))
                .telephoneNumber(ctx.getStringAttribute("telephoneNumber"))
                .homePhone(ctx.getStringAttribute("HomePhone"))
                .mobilePhone(ctx.getStringAttribute("MobilePhone"))
                .country(ctx.getStringAttribute("Country"))
                .state(ctx.getStringAttribute("State"))
                .city(ctx.getStringAttribute("City"))
                .street(ctx.getStringAttribute("street"))
                .postalCode(ctx.getStringAttribute("PostalCode"))
                .company(ctx.getStringAttribute("Company"))
                .organization(ctx.getStringAttribute("Organization"))
                .division(ctx.getStringAttribute("Division"))
                .department(ctx.getStringAttribute("Department"))
                .office(ctx.getStringAttribute("Office"))
                .manager(ctx.getStringAttribute("Manager"))
                .employeeId(ctx.getStringAttribute("EmployeeID"))
                .employeeNumber(ctx.getStringAttribute("EmployeeNumber"))
                .mail(ctx.getStringAttribute("mail"))
                .mailNickname(ctx.getStringAttribute("mailNickname"))
                .samAccountName(ctx.getStringAttribute("SamAccountName"))
                .officePhone(ctx.getStringAttribute("OfficePhone"))
                .telephoneNumber(ctx.getStringAttribute("ipPhone"))
                .title(ctx.getStringAttribute("Title"))
                .userAccountControl(ctx.getStringAttribute("userAccountControl"))
                .enabled(ctx.getStringAttribute("Enabled"))
                .build();
    }

    public void toContext(LdapEntity ldapEntity, DirContextAdapter context) {
        if (ldapEntity.getCommonName() != null) {
            context.setAttributeValue("cn", ldapEntity.getCommonName());
        }
        if (ldapEntity.getCanonicalName() != null) {
            context.setAttributeValue("CanonicalName", ldapEntity.getCanonicalName());
        }
        if (ldapEntity.getGuid() != null) {
            context.setAttributeValue("ObjectGUID", ldapEntity.getGuid());
        }
        if (ldapEntity.getUserPrincipalName() != null) {
            context.setAttributeValue("userPrincipalName", ldapEntity.getUserPrincipalName());
        }
        if (ldapEntity.getDisplayName() != null) {
            context.setAttributeValue("displayName", ldapEntity.getDisplayName());
        }
        if (ldapEntity.getFullName() != null) {
            context.setAttributeValue("name", ldapEntity.getFullName());
        }
        if (ldapEntity.getFirstname() != null) {
            context.setAttributeValue("givenName", ldapEntity.getFirstname());
        }
        if (ldapEntity.getLastname() != null) {
            context.setAttributeValue("sn", ldapEntity.getLastname());
        }
        if (ldapEntity.getOtherName() != null) {
            context.setAttributeValue("OtherName", ldapEntity.getOtherName());
        }
        if (ldapEntity.getInitials() != null) {
            context.setAttributeValue("initials", ldapEntity.getInitials());
        }
        if (ldapEntity.getTelephoneNumber() != null) {
            context.setAttributeValue("telephoneNumber", ldapEntity.getTelephoneNumber());
        }
        if (ldapEntity.getHomePhone() != null) {
            context.setAttributeValue("homePhone", ldapEntity.getHomePhone());
        }
        if (ldapEntity.getMobilePhone() != null) {
            context.setAttributeValue("mobile", ldapEntity.getMobilePhone());
        }
        if (ldapEntity.getCountry() != null) {
            context.setAttributeValue("country", ldapEntity.getCountry());
        }
        if (ldapEntity.getState() != null) {
            context.setAttributeValue("st", ldapEntity.getState());
        }
        if (ldapEntity.getCity() != null) {
            context.setAttributeValue("l", ldapEntity.getCity());
        }
        if (ldapEntity.getStreet() != null) {
            context.setAttributeValue("streetAddress", ldapEntity.getStreet());
        }
        if (ldapEntity.getPostalCode() != null) {
            context.setAttributeValue("postalCode", ldapEntity.getPostalCode());
        }
        if (ldapEntity.getCompany() != null) {
            context.setAttributeValue("company", ldapEntity.getCompany());
        }
        if (ldapEntity.getOrganization() != null) {
            context.setAttributeValue("organization", ldapEntity.getOrganization());
        }
        if (ldapEntity.getDivision() != null) {
            context.setAttributeValue("division", ldapEntity.getDivision());
        }
        if (ldapEntity.getDepartment() != null) {
            context.setAttributeValue("department", ldapEntity.getDepartment());
        }
        if (ldapEntity.getOffice() != null) {
            context.setAttributeValue("physicalDeliveryOfficeName", ldapEntity.getOffice());
        }
        if (ldapEntity.getManager() != null) {
            context.setAttributeValue("manager", ldapEntity.getManager());
        }
        if (ldapEntity.getEmployeeId() != null) {
            context.setAttributeValue("employeeID", ldapEntity.getEmployeeId());
        }
        if (ldapEntity.getEmployeeNumber() != null) {
            context.setAttributeValue("employeeNumber", ldapEntity.getEmployeeNumber());
        }
        if (ldapEntity.getMail() != null) {
            context.setAttributeValue("mail", ldapEntity.getMail());
        }
        if (ldapEntity.getMailNickname() != null) {
            context.setAttributeValue("mailNickname", ldapEntity.getMailNickname());
        }
        if (ldapEntity.getSamAccountName() != null) {
            context.setAttributeValue("sAMAccountName", ldapEntity.getSamAccountName());
        }
        if (ldapEntity.getOfficePhone() != null) {
            context.setAttributeValue("officePhone", ldapEntity.getOfficePhone());
        }
        if (ldapEntity.getIpPhone() != null) {
            context.setAttributeValue("ipPhone", ldapEntity.getIpPhone());
        }
        if (ldapEntity.getTitle() != null) {
            context.setAttributeValue("title", ldapEntity.getTitle());
        }
        if (ldapEntity.getUserAccountControl() != null) {
            context.setAttributeValue("userAccountControl", ldapEntity.getUserAccountControl());
        }
        if (ldapEntity.getEnabled() != null) {
            context.setAttributeValue("enabled", ldapEntity.getEnabled());
        }
    }
}
