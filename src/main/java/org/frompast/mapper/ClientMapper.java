package org.frompast.mapper;

import org.frompast.domain.entity.Client;
import org.frompast.web.dto.user.ClientCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ClientMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(source = "fullName", target = "fullName")
    @Mapping(source = "firstname", target = "firstname")
    @Mapping(source = "lastname", target = "lastname")
    @Mapping(source = "mobilePhone", target = "mobilePhone")
    @Mapping(source = "country", target = "country")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "street", target = "street")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "mail", target = "mail")
    Client toEntity(ClientCreateDto createDto);
}
