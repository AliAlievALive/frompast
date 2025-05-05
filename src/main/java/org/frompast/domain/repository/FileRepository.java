package org.frompast.domain.repository;

import org.frompast.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
    boolean existsByUserIdAndFileUrl(Long userId, String fileUrl);

    Optional<File> findByIdAndUserId(Long fileId, Long userId);
}
