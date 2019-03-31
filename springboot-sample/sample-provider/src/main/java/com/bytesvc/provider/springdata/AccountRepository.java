package com.bytesvc.provider.springdata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bytesvc.provider.model.Account;

import java.util.Optional;

@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByIdentifier(String identifier);

}
