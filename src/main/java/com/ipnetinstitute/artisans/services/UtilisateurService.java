package com.ipnetinstitute.artisans.services;

import com.ipnetinstitute.artisans.dto.UtilisateurDto;
import com.ipnetinstitute.artisans.entities.Utilisateur;
import com.ipnetinstitute.artisans.exception.ObjectNotFoundInDBException;

public interface UtilisateurService {
    Utilisateur retrieveUserByUsername(String username);
    UtilisateurDto findUserByUsername(String username) throws ObjectNotFoundInDBException;
    UtilisateurDto registerUser(UtilisateurDto utilisateurDto);
}
