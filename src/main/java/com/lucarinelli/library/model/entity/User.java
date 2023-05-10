package com.lucarinelli.library.model.entity;

import com.lucarinelli.library.model.RentalModel;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Builder
@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;
    private String fiscalCode;
    private String name;
    private String surname;
    private List<RentalModel> rentalsList;

    public void addRental(RentalModel rentalModel) {
        this.rentalsList.add(rentalModel);
    }

}
