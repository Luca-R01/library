package com.lucarinelli.library.model.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDtoSearch {

    private String fiscalCode;
    private String name;
    private String surname;
}
