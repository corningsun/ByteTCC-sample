package com.bytesvc.provider.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.interfaces.IAccountService;
import com.bytesvc.provider.model.Account;

/**
 * @author corning
 */
@Slf4j
@Service("accountServiceCancel")
public class AccountServiceCancel implements IAccountService {

    @Autowired
    private IAccountDao accountDao;

    /**
     * 取消冻结
     *
     * @param acctId
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void increaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() - amount);
        this.accountDao.update(account);
        log.debug("undo increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void decreaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() + amount);
        this.accountDao.update(account);
        log.debug("undo decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

}
