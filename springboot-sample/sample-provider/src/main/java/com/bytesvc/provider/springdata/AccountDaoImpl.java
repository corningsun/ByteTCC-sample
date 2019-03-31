package com.bytesvc.provider.springdata;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao {

	@Autowired
	private AccountRepository repository;

	@Override
	public Account findByIdentifier(String identifier) {
		return repository.findByIdentifier(identifier)
				.orElseGet(() -> create(identifier));
	}

	private Account create(String identifier) {
		Account account = new Account();
		account.setIdentifier(identifier);
		account.setFrozen(0);
		account.setAmount(0);
		return repository.saveAndFlush(account);
	}

	@Override
	public void insert(Account account) {
		this.repository.saveAndFlush(account);
	}

	@Override
	public void update(Account account) {
		this.repository.saveAndFlush(account);
	}

	@Override
	public void delete(Account account) {
		this.repository.delete(account);
	}

}
