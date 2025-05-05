package org.frompast.web.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "User file")
public class FileReadDto {
    @Schema(name = "id",
            title = "Id файла",
            description = "Не заполняется при добавлении нового значения свойства")
    Long id;

    @Schema(name = "userId",
            title = "Id пользователя",
            description = "Должно быть заполнено при добавлении нового значения свойства")
    Long userId;

    @Schema(name = "fileUrl",
            title = "URL файла",
            description = "Должно быть заполнено при добавлении нового значения свойства")
    String fileUrl;
}
