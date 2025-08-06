package br.com.lessa.financialcontrol.service;

import br.com.lessa.financialcontrol.dto.AccountCredentialDTO;
import br.com.lessa.financialcontrol.dto.TokenDTO;
import br.com.lessa.financialcontrol.entity.User;
import br.com.lessa.financialcontrol.repository.UserRepository;
import br.com.lessa.financialcontrol.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private UserRepository userRepository;

    public ResponseEntity<TokenDTO> signIn(AccountCredentialDTO credential) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        credential.username(),
                        credential.password()
                )
        );

        var user =  userRepository.findByUsername(credential.username());
        if (user == null){
            throw new UsernameNotFoundException("Username " + credential.username() + " not found");
        }
        var token = jwtTokenProvider.createAccessToken(
                credential.username(),
                user.getRoles()
        );
        return ResponseEntity.ok(token);
    }

    public ResponseEntity<TokenDTO> refreshToken(String username, String refreshToken ){
        var user = userRepository.findByUsername(username);
        TokenDTO token;
        if (user != null) {
            token = jwtTokenProvider.refreshToken(refreshToken);
        }
        else {
            throw new UsernameNotFoundException("Username " + username + " not found!");
        }
        return ResponseEntity.ok(token);
    }

    public AccountCredentialDTO create(AccountCredentialDTO user) {

        if (user == null) throw new NullPointerException("User is null");

        log.info("Creating one new User!");

        var entity = new User();

        entity.setUsername(user.fullname());
        entity.setPassword(generateHashedPassword(user.password()));

        var dto = userRepository.save(entity);
        return new AccountCredentialDTO(dto.getUsername(), dto.getPassword(), dto.getFullname());
    }

    private String generateHashedPassword(String password) {

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("",
                8,
                185000,
                Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);

        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);

        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder.encode(password);
    }


}
