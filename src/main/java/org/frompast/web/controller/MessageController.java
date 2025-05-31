package org.frompast.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.Client;
import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.User;
import org.frompast.domain.entity.group.Create;
import org.frompast.mapper.MessageMapper;
import org.frompast.service.ClientService;
import org.frompast.service.MessageService;
import org.frompast.service.UserService;
import org.frompast.web.dto.message.MessageCreateDto;
import org.frompast.web.dto.message.MessageReadDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.frompast.utils.JwtUtil.getUserGuid;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    MessageService service;
    UserService userService;
    MessageMapper mapper;
    private final ClientService clientService;

    @Operation(summary = "Get by id")
    @GetMapping("/{id}")
    public MessageReadDto getById(@PathVariable("id") Long id) {
        Message entity = service.getById(id);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Create file")
    @PostMapping
    public MessageReadDto create(@RequestBody @Validated({Create.class, Default.class}) MessageCreateDto dto) {
        User user = userService.getByGuid(getUserGuid());
        Client client = clientService.getById(dto.getClientId());
        Message entity = service.create(mapper.fromCreateDto(dto, user));
        entity.setClient(client);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Delete file by id")
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
    }

    @Operation(summary = "Add files to message")
    @PatchMapping("/{id}")
    public MessageReadDto addFilesToMessage(@PathVariable("id") Long id, @RequestBody List<Long> fileIds) {
        Message entity = service.addFilesToMessage(id, fileIds);
        return mapper.toReadDto(entity);
    }
}
