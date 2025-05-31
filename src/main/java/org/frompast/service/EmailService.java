package org.frompast.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.frompast.web.dto.EmailDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service

public class EmailService {

    private static final String EMAIL_TEXT = """
            Event uid: %s

            Message:
            %s

            %s""";

    JavaMailSender javaMailSender;

    @NonFinal
    @Value("${spring.mail.sender}")
    String sender;

    public void sendSimpleMail(EmailDetails details) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(details.getAddressee());
        mailMessage.setSubject(details.getSubject());
        mailMessage.setText(EMAIL_TEXT.formatted(details.getEventUid(), details.getMessage(), details.getUrl()));
        javaMailSender.send(mailMessage);
    }


}
