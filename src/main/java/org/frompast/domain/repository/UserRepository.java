package org.frompast.domain.repository;

import org.frompast.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findBySamAccountNameIgnoreCase(String samAccountName);

    List<User> findAllByGuidIsIn(Collection<String> ids);

    Optional<User> findByGuid(String samAccountName);

    List<User> findAllByDisplayNameIn(Collection<String> displayName);

    @Transactional
    @Modifying
    @Query("update User u set u.lastLogin = ?1 where u.guid = ?2")
    void updateLastLoginByGuid(LocalDateTime lastLogin, String id);
}
