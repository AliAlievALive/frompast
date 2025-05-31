package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.Client;
import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.User;
import org.frompast.domain.repository.ClientRepository;
import org.frompast.mapper.ClientMapper;
import org.frompast.web.dto.user.ClientCreateDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        User byGuid = userService.getByGuid(getUserGuid());
        Client entity = mapper.toEntity(createDto);
        entity.setUser(byGuid);
        repository.save(entity);
    }

    public void saveMessage(Long clientId, Message message) {
        Client client = repository.findById(clientId).orElseThrow();
        client.getMessages().add(message);
        repository.save(client);
    }

    public Client getById(Long clientId) {
        return repository.findById(clientId).orElseThrow();
    }
}
