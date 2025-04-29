package org.frompast.task;

import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.frompast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;


@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserActualizationTask {

    @Setter(onMethod_ = {@Autowired, @Qualifier("userService")})
    UserService userService;

    @Scheduled(cron = "0 0 1 * * ?", zone = "Europe/Moscow")
    public void doWork() {
        userService.actualAllUsers();
    }

}
