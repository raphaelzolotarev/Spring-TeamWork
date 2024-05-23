package com.example.springteamwork.model;
import lombok.*;
import jakarta.persistence.*;
import org.apache.catalina.User;


import java.util.Date;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "post")
public class Post extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Lob
    @Column(name = "text", columnDefinition = "LONGTEXT")
    private String description;

}
