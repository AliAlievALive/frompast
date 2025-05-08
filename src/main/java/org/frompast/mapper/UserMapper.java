package org.frompast.mapper;

import org.frompast.domain.entity.LdapEntity;
import org.frompast.domain.entity.User;
import org.frompast.utils.GuidHelper;
import org.frompast.web.dto.user.UserReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(uses = FileMapper.class)
public interface UserMapper {

    default User fromLdapEntity(LdapEntity source) {
        String distinguishedName = source.getDistinguishedName().toString();
        String guid = GuidHelper.convertByteArrayToGuidString(source.getGuid());
        return fromLdapEntity(source, distinguishedName, guid);
    }

    UserReadDto toReadDto(User user);

    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "distinguishedName", source = "distinguishedName")
    @Mapping(target = "guid", source = "guid")
    @Mapping(target = "syncDate", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    User fromLdapEntity(LdapEntity entity, String distinguishedName, String guid);

    List<User> fromLdapEntityList(List<LdapEntity> entities);

    default void updateFromLdapEntity(User updated, LdapEntity updating) {
        String distinguishedName = updating.getDistinguishedName().toString();
        String guid = GuidHelper.convertByteArrayToGuidString(updating.getGuid());
        updateFromLdapEntity(updated, updating, distinguishedName, guid);
    }

    @Mapping(target = "clients", ignore = true)
    @Mapping(target = "distinguishedName", source = "distinguishedName")
    @Mapping(target = "guid", source = "guid")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "files", ignore = true)
    @Mapping(target = "isActive", ignore = true)
    @Mapping(target = "syncDate", ignore = true)
    void updateFromLdapEntity(@MappingTarget User updated, LdapEntity updating, String distinguishedName, String guid);
}
