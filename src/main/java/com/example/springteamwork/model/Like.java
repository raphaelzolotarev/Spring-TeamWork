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
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private long user_id;

    @NotNull
    private long post_id;

    public Like(long user_id, long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
}