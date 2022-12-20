package com.fwitter.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import java.sql.Date;


@Data
@AllArgsConstructor
public class RegistrationObject {
    private String firstName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
}
