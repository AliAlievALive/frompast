package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.File;
import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.ScheduleType;
import org.frompast.domain.entity.User;
import org.frompast.domain.repository.MessageRepository;
import org.frompast.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class MessageService {
    MessageRepository repository;
    FileService fileService;
    EmailService emailService;
    private final MessageMapper messageMapper;

    public Message getById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Message create(Message message) {
        return repository.save(message);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Message addFilesToMessage(Long id, List<Long> fileIds) {
        Set<File> files = fileService.getByIdIn(fileIds)
                .collect(Collectors.toSet());
        Message message = getById(id);
        message.setFiles(files);
        return repository.save(message);
    }

    @Transactional
    public void sendMessagesToClient(List<Message> messages) {
        sendByEmail(messages);
    }

    private void sendByEmail(List<Message> messages) {
        messages.forEach(message -> emailService
                .sendSimpleMail(messageMapper.toSimpleDetails(message, UUID.randomUUID())));
    }

    public List<Message> checkMessages(ScheduleType scheduleType, List<User> users) {
        if (users == null || users.isEmpty()) return List.of();

        LocalDateTime now = LocalDateTime.now();

        Map<Long, Integer> userToElapsedUnits = users.stream()
                .collect(Collectors.toMap(
                        User::getId,
                        user -> {
                            Duration duration = Duration.between(user.getLastLogin(), now);
                            return switch (scheduleType) {
                                case HOUR -> (int) duration.toHours();
                                case DAY -> (int) duration.toDays();
                                case WEEK -> (int) (duration.toDays() / 7);
                                case MONTH -> (int) ChronoUnit.MONTHS.between(user.getLastLogin(), now);
                                case YEAR -> (int) ChronoUnit.YEARS.between(user.getLastLogin(), now);
                            };
                        }
                ));

        Set<Long> userIds = userToElapsedUnits.entrySet().stream()
                .filter(e -> e.getValue() > 0)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        if (userIds.isEmpty()) return List.of();

        List<Message> allCandidateMessages = repository.findByOwner_IdInAndScheduleType(userIds, scheduleType);

        return allCandidateMessages.stream()
                .filter(msg -> {
                    Integer elapsed = userToElapsedUnits.get(msg.getOwner().getId());
                    return elapsed != null && msg.getWaitingTime() <= elapsed;
                })
                .toList();
    }
}
