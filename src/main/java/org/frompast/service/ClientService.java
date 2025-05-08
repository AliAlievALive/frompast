package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.Client;
import org.frompast.domain.repository.ClientRepository;
import org.frompast.mapper.ClientMapper;
import org.frompast.web.dto.user.ClientCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class ClientService {
    ClientRepository repository;
    ClientMapper mapper;

    @Transactional
    public void createClientForSendingMessage(ClientCreateDto createDto) {
        Client entity = mapper.toEntity(createDto);
        repository.save(entity);
    }
}
