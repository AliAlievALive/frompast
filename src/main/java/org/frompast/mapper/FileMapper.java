package org.frompast.mapper;

import org.frompast.domain.entity.File;
import org.frompast.web.dto.file.FileCreateDto;
import org.frompast.web.dto.file.FileReadDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper
public interface FileMapper {

    @Mapping(target = "userId", source = "file.user.id")
    FileReadDto toReadDto(File file);

    List<FileReadDto> toReadDtoList(Iterable<File> files);

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "saveDate", expression = "java(java.time.LocalDateTime.now())")
    File fromCreateDto(FileCreateDto source);

    @Mapping(target = "message", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "saveDate", expression = "java(java.time.LocalDateTime.now())")
    void updateFromCreateDto(@MappingTarget File target, FileCreateDto source);

}
