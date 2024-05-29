package com.example.springteamwork.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.Base64;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends AuditModel{

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

    @NotNull
    private String tag;

    @NotNull
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;

    @Transient
    private MultipartFile file;

    public Post(User author, String title, String description, String tag, byte[] image) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.tag = tag;
        this.image = image;
    }

    public String getImageBase64() {
        return Base64.getEncoder().encodeToString(this.image);
    }

}
