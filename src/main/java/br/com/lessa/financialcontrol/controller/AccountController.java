package br.com.lessa.financialcontrol.controller;

import br.com.lessa.financialcontrol.dto.AccountRequestDTO;
import br.com.lessa.financialcontrol.dto.AccountResponseDTO;
import br.com.lessa.financialcontrol.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping
    public ResponseEntity<AccountResponseDTO> createAccount(@RequestBody AccountRequestDTO accountRequestDTO){
        return ResponseEntity.ok(accountService.createAccount(accountRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponseDTO> getAccount(@PathVariable String id){
        return ResponseEntity.ok(accountService.getAccount(id));
    }
}
