package com.ipnetinstitute.artisans.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString()
public class Posts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  long  postId ;
   @Column(unique = true)
    private String code ;
    private String libelle ;
    private String description ;
    private LocalDateTime datePost ;
    private int status  ; // 0 supprime, 1 created , 2 publié
    private LocalDateTime dateCreatedPost ;
    @ManyToOne
    @JoinColumn(name = "artisanId", nullable = false)
    private Artisans artisans ;


}
