package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.frompast.domain.entity.Message;
import org.frompast.domain.entity.ScheduleType;
import org.frompast.domain.entity.User;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Slf4j
@Service
public class ScheduleService {
    UserService userService;
    MessageService messageService;

    @Scheduled(cron = "0 0 * * * *") // каждый час
    public void checkUsersLastLoggedTimeHour() {
        processInactiveUsers(ScheduleType.HOUR,
                LocalDateTime.now().minusHours(1),
                LocalDateTime.now());
    }

    @Scheduled(cron = "0 0 0 * * *") // каждый день в полночь
    public void checkUsersLastLoggedTimeDay() {
        processInactiveUsers(ScheduleType.DAY,
                LocalDateTime.now().minusDays(1),
                LocalDateTime.now().minusHours(1));
    }

    @Scheduled(cron = "0 0 1 * * MON") // каждую неделю в понедельник в 01:00
    public void checkUsersLastLoggedTimeWeek() {
        processInactiveUsers(ScheduleType.WEEK,
                LocalDateTime.now().minusWeeks(1),
                LocalDateTime.now().minusDays(1));
    }

    @Scheduled(cron = "0 0 2 1 * *") // каждый месяц 1 числа в 02:00
    public void checkUsersLastLoggedTimeMonth() {
        processInactiveUsers(ScheduleType.MONTH,
                LocalDateTime.now().minusMonths(1),
                LocalDateTime.now().minusWeeks(1));
    }

    @Scheduled(cron = "0 0 3 1 1 *") // каждый год 1 января в 03:00
    public void checkUsersLastLoggedTimeYear() {
        processInactiveUsers(ScheduleType.YEAR,
                LocalDateTime.now().minusYears(1),
                LocalDateTime.now().minusMonths(1));
    }

    private void processInactiveUsers(ScheduleType type, LocalDateTime from, LocalDateTime to) {
        List<User> users = userService.findUsersLastLoggedBetween(from, to);
        log.info("Processing {} users for {}", users.size(), type);
        List<Message> messages = messageService.checkMessages(type, users);
        messageService.sendMessagesToClient(messages);
    }
}
