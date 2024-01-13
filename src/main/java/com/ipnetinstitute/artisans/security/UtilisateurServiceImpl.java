package com.ipnetinstitute.artisans.security;

import com.ipnetinstitute.artisans.dao.UtilisateurDao;
import com.ipnetinstitute.artisans.dto.UtilisateurDto;
import com.ipnetinstitute.artisans.entities.Utilisateur;
import com.ipnetinstitute.artisans.exception.ObjectNotFoundInDBException;
import com.ipnetinstitute.artisans.mappers.UtilisateurMapper;
import com.ipnetinstitute.artisans.services.UtilisateurService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
    private final UtilisateurDao utilisateurDao ;
    private final UtilisateurMapper utilisateurMapper;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, UtilisateurMapper utilisateurMapper) {
        this.utilisateurDao = utilisateurDao;
        this.utilisateurMapper = utilisateurMapper;
    }

    @Override
    public Utilisateur retrieveUserByUsername(String email) {
        return utilisateurDao.findByEmail(email).orElse(null);
    }

    @Override
    public UtilisateurDto findUserByUsername(String email) throws ObjectNotFoundInDBException {
        Utilisateur utilisateur = utilisateurDao.findByEmail(email)
                .orElseThrow(()-> new ObjectNotFoundInDBException("Utilisateur", email));
        return utilisateurMapper.fromUtilisateur(utilisateur);
    }

    @Override
    public UtilisateurDto registerUser(UtilisateurDto utilisateurDto) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = retrieveUserByUsername(email);
        if(utilisateur == null){
            throw new UsernameNotFoundException("Utilisateur non trouvé pour cet email:" + email);
        }
        return new UserPrincipal(utilisateur);
    }
}
