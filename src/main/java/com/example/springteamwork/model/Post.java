package com.example.springteamwork.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "posts")

public class Post extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(unique = true)
    private String title;

    @NotNull
    @Size(max = 250)
    private String description;

    @NotNull
    @Lob
    @Column(name = "content", columnDefinition = "LONGTEXT")
    private String content;


    public Post(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public Post(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public Post(String title, String description, String content) {
        this.title = title;
        this.description = description;
        this.content = content;
    }

}