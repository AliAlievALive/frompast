package org.frompast.service;

import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.File;
import org.frompast.domain.entity.User;
import org.frompast.domain.entity.group.Create;
import org.frompast.domain.entity.group.Update;
import org.frompast.domain.repository.FileRepository;
import org.frompast.exceptions.CustomException;
import org.frompast.mapper.FileMapper;
import org.frompast.web.dto.file.FileCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class FileService {

    FileRepository repository;
    FileMapper mapper;
    UserService userService;

    public File getById(Long id) {
        return findById(id).orElseThrow(() -> new CustomException("file not found"));
    }

    @Transactional
    public File create(@Validated({Create.class, Default.class}) FileCreateDto dto) {
        if (repository.existsByUserIdAndFileUrl(dto.getUserId(), dto.getFileUrl())) {
            throw new CustomException("File with url: %s for user id: %s already exists"
                    .formatted(dto.getFileUrl(), dto.getUserId()));
        }

        File entity = mapper.fromCreateDto(dto);

        User user = userService.getById(dto.getUserId());
        entity.setUser(user);

        return repository.save(entity);
    }

    @Transactional
    public File update(@Validated({Update.class, Default.class}) FileCreateDto dto) {
        File entity = getById(dto.getId());
        mapper.updateFromCreateDto(entity, dto);

        User user = userService.getById(dto.getUserId());
        entity.setUser(user);

        return repository.save(entity);
    }

    @Transactional
    public void deleteById(Long id) {
        File entity = getById(id);
        repository.delete(entity);
    }

    public Optional<File> findById(Long id) {
        return repository.findById(id);
    }

    public File getByUserIdAndFileId(Long userId, Long fileId) {
        return repository.findByIdAndUserId(fileId, userId)
                .orElseThrow(() -> new CustomException("%s not found by fileId: %s, userId: %s"
                        .formatted(File.class.getSimpleName(), fileId, userId)));
    }

}
