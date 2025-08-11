package br.com.lessa.financialcontrol.repository;

import br.com.lessa.financialcontrol.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
}
