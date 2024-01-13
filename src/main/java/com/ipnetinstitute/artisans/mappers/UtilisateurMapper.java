package com.ipnetinstitute.artisans.mappers;

import com.ipnetinstitute.artisans.dto.UtilisateurDto;
import com.ipnetinstitute.artisans.entities.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", ignore = true)
    Utilisateur toUtilisatilisateur(UtilisateurDto utilisateurDto);

    UtilisateurDto fromUtilisateur(Utilisateur utilisateur) ;
}
