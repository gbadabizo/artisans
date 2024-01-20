package com.ipnetinstitute.artisans.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Set;

@Entity
@Data
public class Utilisateur implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(unique = true)
    private String email ;
    private String password;
    private String nom;
    private String prenoms;
    private int status ;
    @ManyToMany
    @JoinTable(
            name="users_role",
            joinColumns = @JoinColumn(
                    name="user_id" , referencedColumnName = "id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name="role_id", referencedColumnName = "id"
            ))
    private Set<Role> roles;
}

