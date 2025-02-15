package com.Acme.game.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecretSantaAssignment {
    private String employeeName;
    private String employeeEmail;
    private String secretChildName;
    private String secretChildEmail;


}

