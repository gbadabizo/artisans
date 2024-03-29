package com.ipnetinstitute.artisans.dao;

import com.ipnetinstitute.artisans.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisateurDao extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String nom) ;
}
