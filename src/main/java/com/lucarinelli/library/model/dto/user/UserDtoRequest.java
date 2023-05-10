package com.lucarinelli.library.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDtoRequest {

    @NotBlank
    private String fiscalCode;

    @NotBlank
    private String name;

    @NotBlank
    private String surname;

}
