package org.frompast.mapper;

import org.frompast.domain.entity.File;
import org.frompast.domain.entity.Message;
import org.frompast.web.dto.message.MessageCreateDto;
import org.frompast.web.dto.message.MessageReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.Set;

@Mapper
public interface MessageMapper {

    @Mapping(target = "fileUrl", source = "message.files", qualifiedByName = "getUrls")
    MessageReadDto toReadDto(Message message);

    @Mapping(target = "files", ignore = true)
    Message fromCreateDto(MessageCreateDto source);

    @Named(value = "getUrls")
    default List<String> getUrls(Set<File> files) {
        return files.stream()
                .map(File::getFileUrl)
                .toList();
    }
}
