package org.frompast.web.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frompast.domain.entity.ScheduleType;
import org.frompast.domain.entity.group.Create;
import org.frompast.domain.entity.group.Update;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "message")
public class MessageCreateDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    @Schema(name = "id",
            title = "Id сообщения",
            description = "Не заполняется при добавлении нового сообщения")
    Long id;

    @Schema(name = "text",
            title = "текст сообщения",
            description = "Должно быть заполнено при добавлении нового сообщения")
    String text;

    @Schema(name = "scheduleType",
            title = "тип ожидания (HOUR, WEEK, MONTH, YEAR)",
            description = "Должно быть заполнено при добавлении нового сообщения")
    ScheduleType scheduleType;

    @Schema(name = "waitingTime",
            title = "количество ожидания до отправки",
            description = "Должно быть заполнено при добавлении нового сообщения")
    Integer waitingTime;
}
