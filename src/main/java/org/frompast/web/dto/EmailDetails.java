package org.frompast.web.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {

    String eventUid;
    String addressee;
    String subject;
    String message;
    String url;

}
