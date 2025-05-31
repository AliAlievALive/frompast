package org.frompast.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.User;
import org.frompast.mapper.UserMapper;
import org.frompast.service.UserService;
import org.frompast.utils.PageParam;
import org.frompast.web.dto.user.UserReadDto;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

    UserService service;
    UserMapper mapper;

    @Operation(summary = "Get user by id")
    @GetMapping("/{id}")
    public UserReadDto getById(@PathVariable Long id) {
        User entity = service.getById(id);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Get user by guid")
    @GetMapping("/by-guid")
    public UserReadDto getByGuid(@RequestParam("guid") UUID id) {
        User entity = service.getByGuid(id);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Get users page")
    @GetMapping
    public Page<UserReadDto> getPage(@ParameterObject PageParam pageParam) {
        Page<User> page = service.getPage(pageParam);
        return page.map(mapper::toReadDto);
    }

    @Operation(summary = "Get user by samAccountName")
    @GetMapping("/filter/sam-account-name")
    public UserReadDto getBySamAccountName(@RequestParam String samAccountName) {
        User entity = service.getBySamAccountName(samAccountName);
        return mapper.toReadDto(entity);
    }

    @Operation(summary = "Get users by guid ids")
    @GetMapping("/filter/guid-list")
    public List<UserReadDto> getUsersByGuidIn(@RequestParam List<UUID> guidList) {
        List<User> users = service.findAllByGuids(guidList.stream().map(UUID::toString).toList());
        return users.stream().map(mapper::toReadDto).toList();
    }

    @Operation(summary = "Get users by display names")
    @GetMapping("/filter/display-name-list")
    public List<UserReadDto> getUsersByDisplayNameIn(@RequestParam Collection<String> displayNames) {
        List<User> users = service.getByDisplayNameIn(displayNames);
        return users.stream().map(mapper::toReadDto).toList();
    }

    @Operation(summary = "update user login time")
    @PostMapping("/guid")
    public void updateUserLoginTimeByUuid(@RequestParam("guid") UUID id) {
        service.updateUserLoginTime(id);
    }
}
