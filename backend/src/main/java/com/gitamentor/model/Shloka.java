package com.gitamentor.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shlokas")
@Data
@NoArgsConstructor
public class Shloka {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String category;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sanskrit;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String meaning;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "life_example", columnDefinition = "TEXT")
    private String lifeExample;
}
