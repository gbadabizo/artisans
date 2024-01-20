package com.ipnetinstitute.artisans.dao;

import com.ipnetinstitute.artisans.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>{
    Optional<Role> findByLibelle(String libelle);

}

