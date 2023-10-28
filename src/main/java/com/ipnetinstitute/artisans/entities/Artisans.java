package com.ipnetinstitute.artisans.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
public class Artisans implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long artisanId ;
    @Column(unique = true)
    private String code  ;
    @Column(nullable = false)
    private  String nom;
    private String prenoms ;
    private String email ;
    @Column(nullable = false)
    private String telephone ;
    @Column(nullable = false)
    private String atelier;
    @Column(nullable = false)
    private String ville ;
    private String adresse ;
    private int status ;




}
