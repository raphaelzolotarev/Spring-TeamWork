package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User {

    public enum Role {
        USER,
        AUTHOR
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'USER'")
    private Role role;

    private boolean isOnline = true;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String retypePassword;

    private String street;
    private String houseNr;
    private String city;
    private String zip;

    public User(String firstName, String lastName, String username, String email, String password, String retypePassword, String street, String houseNr, String city, String zip) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.retypePassword = retypePassword;
        this.street = street;
        this.houseNr = houseNr;
        this.city = city;
        this.zip = zip;
    }

}
