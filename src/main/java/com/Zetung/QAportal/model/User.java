package com.Zetung.QAportal.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
}
