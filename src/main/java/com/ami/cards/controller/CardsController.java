package com.ami.cards.controller;

import com.ami.cards.constants.CardsConstants;
import com.ami.cards.dto.CardsContactInfoDTO;
import com.ami.cards.dto.CardsDTO;
import com.ami.cards.dto.ErrorResponseDTO;
import com.ami.cards.dto.ResponseDTO;
import com.ami.cards.service.CardsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name="CRUD REST APIs for Cards in Bank",
        description = "CRUD REST APIs to CREATE, UPDATE, FETCH and DELETE CARDS"
)
@RestController
@RequestMapping("/api")
@Validated
public class CardsController {

    private CardsService cardsService;

    public CardsController(CardsService cardsService){
        this.cardsService=cardsService;
    }

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private Environment environment;

    @Autowired
    private CardsContactInfoDTO cardsContactInfoDTO;

    @Operation(
            summary = "CREATE CARD REST API",
            description = "REST API to create new card for Customer based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Http Status Created"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createCard(@RequestParam
                                                  @Pattern(regexp = "(^$|[0-9]){10}",message = "Mobile Number should be 10 digits")
                                                  String mobileNumber){
        cardsService.createCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(CardsConstants.STATUS_201,CardsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "FETCH CARD REST API",
            description = "REST API to fetch card details of Customer based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/fetch")
    public ResponseEntity<CardsDTO> fetchCardDetails(@RequestParam
                                                            @Pattern(regexp = "(^$|[0-9]){10}",message = "Mobile Number should be 10 digits")
                                                                    String mobileNumber){
        CardsDTO cardsDTO=cardsService.fetchCard(mobileNumber);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsDTO);
    }

    @Operation(
            summary = "UPDATE CARD REST API",
            description = "REST API to update card details of Customer"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode="417",
                    description = "Http Status Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCard(@Valid @RequestBody CardsDTO cardsDTO){
        boolean isUpdated= cardsService.updateCard(cardsDTO);
        if(isUpdated){
            return ResponseEntity.ok(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "DELETE CARD REST API",
            description = "REST API to delete card of Customer based on mobile number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Http Status OK"
            ),
            @ApiResponse(
                    responseCode="417",
                    description = "Http Status Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Http Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteCard(@RequestParam
                                                      @Pattern(regexp = "(^$|[0-9]){10}",message = "Mobile Number should be 10 digits")
                                                              String mobileNumber){
        boolean isDeleted= cardsService.deleteCard(mobileNumber);
        if(isDeleted){
            return ResponseEntity.ok(new ResponseDTO(CardsConstants.STATUS_200,CardsConstants.MESSAGE_200));
        }else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDTO(CardsConstants.STATUS_417,CardsConstants.MESSAGE_417_DELETE));
        }
    }

    @Operation(
            summary = "Get Accounts Build Information",
            description = "Build Info details of Cards Service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/build-info")
    public ResponseEntity<String> getBuildInfo(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(buildVersion);
    }

    @Operation(
            summary = "Get Maven Information",
            description = "Maven details of Cards service"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/maven-version")
    public ResponseEntity<String> getMavenVersion(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(environment.getProperty("M2_HOME"));
    }

    @Operation(
            summary = "Get Cards Contact Information",
            description = "Contact Info details in case of any issues"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP INTERNAL SERVER ERROR",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResponseDTO.class
                            )
                    )
            )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<CardsContactInfoDTO> getContactInfo(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cardsContactInfoDTO);
    }
}
