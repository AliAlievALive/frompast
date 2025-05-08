package org.frompast.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.groups.Default;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.group.Create;
import org.frompast.service.ClientService;
import org.frompast.web.dto.user.ClientCreateDto;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService service;

    @Operation(summary = "add client for send messages")
    @PostMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createClient(@RequestBody @Validated({Create.class, Default.class}) ClientCreateDto createDto) {
        service.createClientForSendingMessage(createDto);
    }
}
