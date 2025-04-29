package org.frompast.domain.repository;

import org.frompast.domain.entity.LdapEntity;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.Optional;

public interface LdapCustomRepository extends LdapRepository<LdapEntity> {
    Optional<LdapEntity> findByCommonName(String cn);
}