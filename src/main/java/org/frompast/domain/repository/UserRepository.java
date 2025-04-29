package org.frompast.domain.repository;

import org.frompast.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySamAccountNameIgnoreCase(String samAccountName);

    List<User> findAllByGuidIsIn(Collection<String> ids);

    Optional<User> findByGuid(String samAccountName);

    List<User> findAllByDisplayNameIn(Collection<String> displayName);
}
