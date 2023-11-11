package com.ipnetinstitute.artisans.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
public class ArtisanRequestDto {
    private String nom;
    private String prenoms ;
    private String email ;
    private String telephone ;
    private String atelier;
    private String ville ;
    private String adresse ;
}
