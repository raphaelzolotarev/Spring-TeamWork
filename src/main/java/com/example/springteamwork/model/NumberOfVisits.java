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
public class NumberOfVisits {
    @Id
    public Byte id;

    public int numberOfVisits;

    public NumberOfVisits(int numberOfVisits) {
        this.numberOfVisits = numberOfVisits;
    }
}
