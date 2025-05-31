package org.frompast.mapper;

import org.frompast.domain.entity.File;
import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.User;
import org.frompast.web.dto.EmailDetails;
import org.frompast.web.dto.message.MessageCreateDto;
import org.frompast.web.dto.message.MessageReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper
public interface MessageMapper {

    @Mapping(target = "fileUrl", source = "message.files", qualifiedByName = "getUrls")
    MessageReadDto toReadDto(Message message);

    @Mapping(target = "files", ignore = true)
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "owner", source = "user")
    @Mapping(target = "id", source = "source.id")
    Message fromCreateDto(MessageCreateDto source, User user);

    @Named(value = "getUrls")
    default List<String> getUrls(Set<File> files) {
        return files.stream()
                .map(File::getFileUrl)
                .toList();
    }

    @Mapping(target = "url", ignore = true)
    @Mapping(target = "subject", source = "message.client.user.commonName")
    @Mapping(target = "message", source = "message.text")
    @Mapping(target = "addressee", source = "message.client.user.mail")
    EmailDetails toSimpleDetails(Message message, UUID eventUid);
}
