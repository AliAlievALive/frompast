package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.File;
import org.frompast.domain.entity.Message;
import org.frompast.domain.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageService {
    MessageRepository repository;
    FileService fileService;

    public Message getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Message create(Message message) {
        return repository.save(message);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Message addFilesToMessage(Long id, List<Long> fileIds) {
        Set<File> files = fileService.getByIdIn(fileIds)
                .collect(Collectors.toSet());
        Message message = getById(id);
        message.setFiles(files);
        return repository.save(message);
    }
}
