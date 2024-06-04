package com.example.springteamwork.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

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

    @NotNull
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Transient
    private MultipartFile file;

    private String token;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Like> likes = new HashSet<>();

    public User(Role role, boolean isOnline, int number_of_visits, String firstName, String lastName, String username, String email, String password, String street, String houseNr, String city, String zip, byte[] image, String token) {
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
        this.token = token;
    }

    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(this.image);
    }

}
