package com.ipnetinstitute.artisans.security;

import com.ipnetinstitute.artisans.dao.RoleDao;
import com.ipnetinstitute.artisans.dao.UtilisateurDao;
import com.ipnetinstitute.artisans.dto.UtilisateurDto;
import com.ipnetinstitute.artisans.entities.Role;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Transactional
@Qualifier("userDetailsService")
@Slf4j
public class UtilisateurServiceImpl implements UtilisateurService, UserDetailsService {
    private final UtilisateurDao utilisateurDao ;
    private final UtilisateurMapper utilisateurMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final RoleDao roleDao ;

    public UtilisateurServiceImpl(UtilisateurDao utilisateurDao, UtilisateurMapper utilisateurMapper, BCryptPasswordEncoder passwordEncoder, RoleDao roleDao) {
        this.utilisateurDao = utilisateurDao;
        this.utilisateurMapper = utilisateurMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleDao = roleDao;
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
        Utilisateur utilisateur = utilisateurMapper.toUtilisatilisateur(utilisateurDto);
        utilisateur.setPassword(encodePassword(utilisateurDto.getPassword()));
        utilisateur.setRoles(Set.of(defaultRole()));
        utilisateur.setStatus(1);
        Utilisateur utilisateurSaved = utilisateurDao.save(utilisateur);
        return utilisateurMapper.fromUtilisateur(utilisateurSaved);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Utilisateur utilisateur = retrieveUserByUsername(email);
        if(utilisateur == null){
            throw new UsernameNotFoundException("Utilisateur non trouvé pour cet email:" + email);
        }
        return new UserPrincipal(utilisateur);
    }
    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
    private Role defaultRole(){
        return roleDao.findByLibelle("ROLE_USER").orElse(null);
    }
}
