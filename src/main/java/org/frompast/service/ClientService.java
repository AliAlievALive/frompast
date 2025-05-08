package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.Client;
import org.frompast.domain.entity.User;
import org.frompast.domain.repository.ClientRepository;
import org.frompast.mapper.ClientMapper;
import org.frompast.web.dto.user.ClientCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.frompast.utils.JwtUtil.getUserGuid;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class ClientService {
    ClientRepository repository;
    ClientMapper mapper;
    UserService userService;

    @Transactional
    public void createClientForSendingMessage(ClientCreateDto createDto) {
        User byGuid = userService.getByGuid(UUID.fromString(getUserGuid()));
        Client entity = mapper.toEntity(createDto);
        entity.setUser(byGuid);
        repository.save(entity);
    }
}
