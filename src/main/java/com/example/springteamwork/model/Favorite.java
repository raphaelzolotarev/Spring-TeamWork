package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long user_id;

    @NotNull
    private Long author_id;

    public Favorite(Long user_id, Long author_id) {
        this.user_id = user_id;
        this.author_id = author_id;
    }
}