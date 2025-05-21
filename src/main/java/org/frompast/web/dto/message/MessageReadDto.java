package org.frompast.web.dto.message;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frompast.domain.entity.ScheduleType;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "message")
public class MessageReadDto {

    @Schema(name = "id",
            title = "Id сообщения")
    Long id;

    @Schema(name = "text",
            title = "текст сообщения")
    String text;

    @Schema(name = "scheduleType",
            title = "тип ожидания (HOUR, WEEK, MONTH, YEAR)")
    ScheduleType scheduleType;

    @Schema(name = "files",
            title = "ссылки на файлы")
    List<String> fileUrl;

    @Schema(name = "waitingTime",
            title = "количество ожидания до отправки")
    Integer waitingTime;
}
