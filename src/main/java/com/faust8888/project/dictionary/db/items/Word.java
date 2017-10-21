package com.faust8888.project.dictionary.db.items;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "word")
@Data
public class Word{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String word;

    private String meaning;

    private String translate;

    private String context;
}
