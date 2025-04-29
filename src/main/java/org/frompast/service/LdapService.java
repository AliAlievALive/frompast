package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import jakarta.validation.constraints.NotNull;
import org.frompast.configuration.properties.LdapCustomProperties;
import org.frompast.domain.entity.LdapEntity;
import org.frompast.domain.repository.LdapCustomRepository;
import org.frompast.mapper.LdapEntityMapper;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

import javax.naming.directory.SearchControls;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class LdapService {

    LdapCustomRepository ldapCustomRepository;
    LdapTemplate ldapTemplate;
    LdapEntityMapper ldapEntityMapper;
    LdapCustomProperties properties;

    public List<LdapEntity> findAll() {
        log.info("Get all LDAP entities");
        return ldapCustomRepository.findAll();
    }

    @SneakyThrows
    public List<LdapEntity> findAllPartially() {
        List<LdapEntity> result = new ArrayList<>();

        SearchControls searchControls = new SearchControls();
        searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
        searchControls.setReturningAttributes(new String[]{"*", "+"});

        PagedResultsCookie cookie = null;
        do {
            PagedResultsDirContextProcessor processor = new PagedResultsDirContextProcessor(500, cookie);
            result.addAll(ldapTemplate.search(
                    "",
                    buildFilter(),
                    searchControls,
                    ldapEntityMapper,
                    processor));
            cookie = processor.getCookie();
        } while (cookie != null && cookie.getCookie() != null);

        return result;
    }

    @NotNull
    private String buildFilter() {
        return "(&(objectClass=user)(|" + properties.getGroups().stream().collect(Collectors.joining(")(memberOf=", "(memberOf=", ")")) + "))";
    }

}
