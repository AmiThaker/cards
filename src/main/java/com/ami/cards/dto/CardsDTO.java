package com.ami.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(
        name = "Cards",
        description = "Schema to hold Cards Details Information"
)
public class CardsDTO {

    @NotEmpty(message="Mobile Number should not be empty!")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Mobile number should be 10 digits")
    @Schema(
            description = "Mobile Number of Customer",
            example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message="Card Number should not be empty!")
    @Pattern(regexp = "(^$|[0-9]{10})",message = "Card number should be 10 digits")
    @Schema(
            description = "Card Number of Customer",
            example = "1234567890"
    )
    private String cardNumber;

    @NotEmpty(message="Card Type should not empty!")
    @Schema(
            description = "Type of the card",
            example = "Credit Card"
    )
    private String cardType;

    @Positive(message="Total Card Limit should be greater than 0")
    @Schema(
            description = "Total Amount Limit available against the card",
            example = "100000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Amount Used should be 0 or greater than 0")
    @Schema(
            description = "Amount Used by the Customer",
            example = "10000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total Available Amount should be 0 or greater than 0")
    @Schema(
            description = "Available Amount against the card",
            example="99000"
    )
    private int availableAmount;
}
