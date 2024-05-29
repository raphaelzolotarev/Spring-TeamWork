package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Role role = Role.USER;

    private boolean isOnline = true;

    private int number_of_visits = 1;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;

    @NotNull
    @Transient
    private String retypePassword;

    private String street;
    private String houseNr;
    private String city;
    private String zip;

    public User(Role role, boolean isOnline, int number_of_visits, String firstName, String lastName, String username, String email, String password, String street, String houseNr, String city, String zip) {
        this.role = role;
        this.isOnline = isOnline;
        this.number_of_visits = number_of_visits;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.street = street;
        this.houseNr = houseNr;
        this.city = city;
        this.zip = zip;
    }
}