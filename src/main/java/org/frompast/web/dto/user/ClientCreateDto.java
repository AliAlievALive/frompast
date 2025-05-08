package org.frompast.web.dto.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.frompast.domain.entity.group.Create;
import org.frompast.domain.entity.group.Update;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "user")
public class ClientCreateDto {
    @Null(groups = Create.class)
    @NotNull(groups = Update.class)
    @Schema(name = "id",
            title = "Id пользователя",
            description = "Не заполняется при добавлении нового пользователя")
    Long id;
    String fullName;
    String firstname;
    String lastname;
    String mobilePhone;
    String country;
    String state;
    String city;
    String street;
    String postalCode;
    String mail;
}
