package com.bytesvc.provider.service;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.interfaces.IAccountService;
import com.bytesvc.provider.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author corning
 */
@Slf4j
@Service("accountServiceConfirm")
public class AccountServiceConfirm implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    /**
     * 完成冻结
     *
     * @param acctId
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void increaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() - amount);
        account.setAmount(account.getAmount() + amount);
        this.accountDao.update(account);
        log.debug("confirm increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void decreaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() + amount);
        account.setAmount(account.getAmount() - amount);
        this.accountDao.update(account);
        log.debug("confirm decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

}
