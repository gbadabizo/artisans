package com.ipnetinstitute.artisans.entities;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class Images implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long imageId;
    @Column(unique = true)
    private String code ;
    private String libelle ;
    @Lob
    private byte[] imageFile ;
    @ManyToOne
    @JoinColumn(name = "postId", nullable = false)
    private Posts posts ;
}
