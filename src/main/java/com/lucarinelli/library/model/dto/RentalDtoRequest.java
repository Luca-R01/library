package com.lucarinelli.library.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RentalDtoRequest {

    @NotBlank
    private String userId;

    @NotBlank
    private String bookId;
}
