package org.frompast.service;

import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.configuration.properties.LdapCustomProperties;
import org.frompast.domain.entity.LdapEntity;
import org.frompast.domain.repository.LdapCustomRepository;
import org.frompast.mapper.LdapEntityMapper;
import org.springframework.ldap.control.PagedResultsCookie;
import org.springframework.ldap.control.PagedResultsDirContextProcessor;
import org.springframework.ldap.core.DirContextAdapter;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import javax.naming.Name;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.ModificationItem;
import javax.naming.directory.SearchControls;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_16LE;

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
        String p = properties.getGroups() != null
                ? properties.getGroups().stream()
                .collect(Collectors.joining(")(memberOf=", "(memberOf=", ")"))
                : "";
        return "(&(objectClass=user)(|" + p + "))";
    }

    @SneakyThrows
    public void create(LdapEntity ldapEntity) {
        log.info("Creating new LDAP user: {}", ldapEntity.getUserPrincipalName());

        DirContextAdapter context = new DirContextAdapter(buildDn(ldapEntity));

        context.setAttributeValues("objectClass", new String[] {"top", "person", "organizationalPerson", "user"});

        ldapEntityMapper.toContext(ldapEntity, context);

        ldapTemplate.bind(context);

        if (ldapEntity.getPassword() != null) {
            ldapTemplate.modifyAttributes(
                    buildDn(ldapEntity),
                    new ModificationItem[] {
                            new ModificationItem(
                                    DirContext.REPLACE_ATTRIBUTE,
                                    new BasicAttribute("unicodePwd", encodePassword(ldapEntity.getPassword())))
                    });
        }
    }

    @SneakyThrows
    private byte[] encodePassword(String password) {
        String quotedPassword = "\"" + password + "\"";
        return quotedPassword.getBytes(UTF_16LE);
    }

    private Name buildDn(LdapEntity ldapEntity) {
        return LdapNameBuilder.newInstance()
                .add("CN", ldapEntity.getCommonName())
                .add(properties.getUserBase())
                .build();
    }
}
