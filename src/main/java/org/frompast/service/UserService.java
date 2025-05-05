package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.LdapEntity;
import org.frompast.domain.entity.User;
import org.frompast.domain.repository.UserRepository;
import org.frompast.mapper.UserMapper;
import org.frompast.utils.GuidHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.frompast.utils.GuidHelper.convertByteArrayToGuidString;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    UserRepository repository;
    UserMapper mapper;
    LdapService ldapService;

    @Transactional
    public void actualAllUsers() {
        List<LdapEntity> ldapEntities = ldapService.findAllPartially();
        List<User> existedUsers = updateExistedUsers(ldapEntities);
        saveNotExistedUsers(existedUsers, ldapEntities);
    }

    private List<User> updateExistedUsers(List<LdapEntity> ldapEntities) {
        List<String> employeeGuids = ldapEntities.stream()
                .map(LdapEntity::getGuid)
                .map(GuidHelper::convertByteArrayToGuidString)
                .toList();
        Map<String, LdapEntity> ldapEntityByGuid = ldapEntities.stream()
                .collect(toMap(entity -> convertByteArrayToGuidString(entity.getGuid()), identity()));

        List<User> existedUsers = repository.findAllByGuidIsIn(employeeGuids);
        Map<String, User> userByGuid = existedUsers.stream()
                .collect(toMap(User::getGuid, identity()));

        existedUsers.forEach(user -> mapper.updateFromLdapEntity(userByGuid.get(user.getGuid()), ldapEntityByGuid.get(user.getGuid())));

        return repository.saveAll(existedUsers);
    }

    private void saveNotExistedUsers(List<User> existedUsers, List<LdapEntity> ldapEntities) {
        List<String> existedUserGuids = existedUsers.stream()
                .map(User::getGuid)
                .toList();

        List<LdapEntity> notExistedUserLdapEntities = ldapEntities.stream()
                .filter(ldapEntity -> !existedUserGuids.contains(convertByteArrayToGuidString(ldapEntity.getGuid())))
                .toList();

        List<User> notExistedUsers = mapper.fromLdapEntityList(notExistedUserLdapEntities);

        repository.saveAll(notExistedUsers);
    }

    public User getById(Long userId) {
        return repository.findById(userId).orElseThrow();
    }
}
