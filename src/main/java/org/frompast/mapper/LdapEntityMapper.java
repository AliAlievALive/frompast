package org.frompast.mapper;

import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.LdapEntity;
import org.mapstruct.Mapper;
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

}
