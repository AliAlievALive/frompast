package org.frompast.domain.repository;

import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.ScheduleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByOwner_IdInAndScheduleType(Set<Long> userIds, ScheduleType scheduleType);
}
