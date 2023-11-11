package com.ipnetinstitute.artisans.dao;

import com.ipnetinstitute.artisans.entities.Artisans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtisanDao extends JpaRepository<Artisans, Long> {

    Optional<Artisans> findByCode(String code) ;
}
