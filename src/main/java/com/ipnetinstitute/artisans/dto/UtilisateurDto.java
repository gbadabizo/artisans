package com.ipnetinstitute.artisans.dto;


import lombok.Data;

@Data
public class UtilisateurDto {
    private Long id ;
    private String email ;
    private String password;
    private String nom;
    private String prenoms;
    private int status ;
}
