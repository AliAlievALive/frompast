package org.frompast.web.dto.file;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.frompast.domain.entity.group.Create;
import org.frompast.domain.entity.group.Update;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "user file")
public class FileCreateDto {

    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    @Schema(name = "id",
            title = "Id файла",
            description = "Не заполняется при добавлении нового файла")
    Long id;

    @Schema(name = "userId",
            title = "Id пользователя",
            description = "Должно быть заполнено при добавлении нового значения свойства")
    Long userId;

    @Schema(name = "fileUrl",
            title = "url файла",
            description = "Должно быть заполнено при добавлении нового значения свойства")
    String fileUrl;


}
