package com.ami.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(
        name = "Response",
        description = "Schema to hold Successful Response Information"
)
public class ResponseDTO {

    @Schema(
            description = "Status Code in the Response"
    )
    private String statusCode;

    @Schema(
            description = "Status Message in the Response"
    )
    private String statusMessage;
}
