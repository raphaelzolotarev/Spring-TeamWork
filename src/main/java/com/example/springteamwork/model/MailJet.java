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
public class MailJet {
    @Id
    public Byte id;

    public String api;
    public String secret;

    public MailJet(String api, String secret) {
        this.api = api;
        this.secret = secret;
    }
}
