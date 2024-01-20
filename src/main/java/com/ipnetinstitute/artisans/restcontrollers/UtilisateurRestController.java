package com.ipnetinstitute.artisans.restcontrollers;

import com.ipnetinstitute.artisans.dto.LoginRequestDto;
import com.ipnetinstitute.artisans.dto.UtilisateurDto;
import com.ipnetinstitute.artisans.exception.ObjectNotFoundInDBException;
import com.ipnetinstitute.artisans.filters.JWTTokenProvider;
import com.ipnetinstitute.artisans.security.UserPrincipal;
import com.ipnetinstitute.artisans.services.UtilisateurService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.ipnetinstitute.artisans.commons.SecurityConstant.JWT_TOKEN_HEADER;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path="/v1/user")
public class UtilisateurRestController {

    private final AuthenticationManager authenticationManager;
    private final UtilisateurService userService;
    private final JWTTokenProvider jwtTokenProvider;

    public UtilisateurRestController(AuthenticationManager authenticationManager, UtilisateurService userService, JWTTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @PostMapping("/login")
    public ResponseEntity<UtilisateurDto> login(@RequestBody LoginRequestDto loginRequestDto) throws ObjectNotFoundInDBException {
        authenticate(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        UtilisateurDto loginUser = userService.findUserByUsername(loginRequestDto.getEmail());
        UserPrincipal userPrincipal = new UserPrincipal(userService.retrieveUserByUsername(loginRequestDto.getEmail()));
        HttpHeaders jwtHeader = getJwtHeader(userPrincipal);
        return new ResponseEntity<>(loginUser, jwtHeader, OK);
    }
    @PostMapping("/register")
    public ResponseEntity<UtilisateurDto> register(@RequestBody UtilisateurDto utilisateurDto) {
        return new ResponseEntity<>(userService.registerUser(utilisateurDto), OK);
    }

    private HttpHeaders getJwtHeader(UserPrincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authenticate(String username, String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
    }

}
