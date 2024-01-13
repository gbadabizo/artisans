package com.ipnetinstitute.artisans.dao;

import com.ipnetinstitute.artisans.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String nom) ;
}
