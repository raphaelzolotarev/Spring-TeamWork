
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
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private User author;

    @NotNull
    private String title;

    @NotNull
    @Lob
    @Column(name = "description", columnDefinition = "LONGTEXT")
    private String description;

    public Post(User author, String title, String description) {
        this.author = author;
        this.title = title;
        this.description = description;
    }
}