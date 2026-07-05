package com.bddevelopersquad.chemistry.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "search_keys", nullable = false)
    private String searchKeys;

    @Column(nullable = false, length = 1000)
    private String equation;

    @Column(length = 1000)
    private String details;

    @Column(name = "keyword_count", nullable = false)
    private int keywordCount;

    public Reaction(String searchKeys, String equation, String details) {
        this.searchKeys = searchKeys;
        this.equation = equation;
        this.details = details;
    }

    @PrePersist
    @PreUpdate
    public void syncKeywordCount() {
        this.keywordCount = this.searchKeys.trim().split("\\s+").length;
    }

}
