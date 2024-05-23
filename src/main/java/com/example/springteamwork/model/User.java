package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String email;
    private String street;
    private String houseNr;
    private String city;
    private String zip;

    @NotNull
    private String password;

    @NotNull
    private String retypePassword;

    public User(String firstName, String lastName, String username, String email, String password, String retypePassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.retypePassword = retypePassword;
    }

    public User(String firstName, String lastName, String username, String email, String street, String houseNr, String city, String zip, String password, String retypePassword) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.street = street;
        this.houseNr = houseNr;
        this.city = city;
        this.zip = zip;
        this.password = password;
        this.retypePassword = retypePassword;
    }

}
