package org.frompast.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.File;
import org.frompast.domain.entity.group.Create;
import org.frompast.domain.entity.group.Update;
import org.frompast.mapper.FileMapper;
import org.frompast.service.FileService;
import org.frompast.web.dto.file.FileCreateDto;
import org.frompast.web.dto.file.FileReadDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/files")
public class FileController {

    FileService service;
    FileMapper mapper;

    @Operation(summary = "Get file by id")
    @GetMapping("/{id}")
    public FileReadDto getById(@PathVariable("id") Long id) {
        File entity = service.getById(id);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Create file")
    @PostMapping
    public FileReadDto create(@RequestBody @Validated({Create.class, Default.class}) FileCreateDto dto) {
        File entity = service.create(dto);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Update file")
    @PutMapping
    public FileReadDto update(@RequestBody @Validated({Update.class, Default.class}) FileCreateDto dto) {
        File updatedEntity = service.update(dto);
        return mapper.toReadDto(updatedEntity);
    }

    @Operation(summary = "Delete file by id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @Operation(summary = "Get file by userId and fileId")
    @GetMapping("/filter/user-and-file")
    public FileReadDto getByUserIdAndFileId(@RequestParam("userId") Long userId,
                                            @RequestParam("fileId") Long fileId) {
        File entity = service.getByUserIdAndFileId(fileId, userId);
        return mapper.toReadDto(entity);
    }

}
