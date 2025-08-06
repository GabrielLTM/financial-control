package br.com.lessa.financialcontrol.controller;

import br.com.lessa.financialcontrol.dto.AccountCredentialDTO;
import br.com.lessa.financialcontrol.service.AuthService;
import io.micrometer.common.util.StringUtils;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {

    private AuthService service;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@RequestBody AccountCredentialDTO credential){
        if (credentialIsInvalid(credential)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        var token = service.signIn(credential);
        if (token == null) return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request");

        return ResponseEntity.ok().body(token);
    }

    private static boolean credentialIsInvalid(AccountCredentialDTO credential) {
        return credential == null || StringUtils.isBlank(credential.username()) || StringUtils.isBlank(credential.password());
    }
}
