package com.lucarinelli.library.model.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.lucarinelli.library.model.rental.RentalModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDtoResponse {

    private String id;
    private String fiscalCode;
    private String name;
    private String surname;
    private List<RentalModel> rentals;

}
