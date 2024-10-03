package com.ami.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Schema(
        name="Error Response",
        description = "Schema to hold Error Response Information"
)
public class ErrorResponseDTO {

    @Schema(
            description="API Path of the request"
    )
    private String apiPath;

    @Schema(
            description = "Status Code of the response"
    )
    private HttpStatus statusCode;

    @Schema(
            description = "Status Message of the response"
    )
    private String statusMessage;

    @Schema(
            description="Time representing when the error happened"
    )
    private LocalDateTime errorTime;
}
