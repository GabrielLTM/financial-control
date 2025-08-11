package br.com.lessa.financialcontrol.service;

import br.com.lessa.financialcontrol.dto.AccountRequestDTO;
import br.com.lessa.financialcontrol.dto.AccountResponseDTO;
import br.com.lessa.financialcontrol.entity.Account;
import br.com.lessa.financialcontrol.repository.AccountRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AccountService {

    private AccountRepository repository;

    public AccountResponseDTO createAccount(AccountRequestDTO accountRequestDTO){

        var account = Account.builder()
                .id(accountRequestDTO.id())
                .name(accountRequestDTO.name())
                .balance(accountRequestDTO.balance())
                .build();

        var entity = repository.save(account);

        return mapToResponse(entity);
    }

    public AccountResponseDTO getAccount(String id){
        var account = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));;
        return mapToResponse(account);
    }

    private AccountResponseDTO mapToResponse(Account account){
        return AccountResponseDTO.builder()
                .id(account.getId())
                .name(account.getName())
                .userId(account.getUser().getId())
                .build();
    }
}
