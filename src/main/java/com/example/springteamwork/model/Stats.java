package com.example.springteamwork.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Stats implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id = 1L;

    @Column(name = "number_of_visitor", nullable = false)
    private int numberOfVisitor = 0;

    public Stats(int numberOfVisitor) {
        this.numberOfVisitor = numberOfVisitor;
    }

}

