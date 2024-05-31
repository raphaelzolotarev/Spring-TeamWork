package com.example.springteamwork.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class MailGun {
    @Id
    public Byte id;
    public String domain;

    public String api;

    public MailGun(String domain, String api) {
        this.domain = domain;
        this.api = api;
    }
}
