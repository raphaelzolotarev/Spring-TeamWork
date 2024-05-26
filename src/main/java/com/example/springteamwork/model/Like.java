package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "likes")
@Getter
@Setter
@NoArgsConstructor
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long user_id;

    @NotNull
    private Long post_id;

    public Like(Long user_id, Long post_id) {
        this.user_id = user_id;
        this.post_id = post_id;
    }
  
}