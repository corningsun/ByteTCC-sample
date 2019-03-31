package com.bytesvc.provider.service;

import com.bytesvc.provider.dao.IAccountDao;
import com.bytesvc.provider.interfaces.IAccountService;
import com.bytesvc.provider.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author corning
 */
@Slf4j
@Compensable(interfaceClass = IAccountService.class,
        cancellableKey = "accountServiceCancel",
        confirmableKey = "accountServiceConfirm"
)
@Service("accountService")
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountDao accountDao;

    /**
     * 冻结积分
     *
     * @param acctId
     * @param amount
     */
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void increaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() + amount);
        this.accountDao.update(account);
        log.debug("exec increase: acct= %s, amount= %7.2f%n", acctId, amount);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void decreaseAmount(String acctId, double amount) {
        Account account = this.accountDao.findByIdentifier(acctId);
        account.setFrozen(account.getFrozen() - amount);
        this.accountDao.update(account);
        log.debug("exec decrease: acct= %s, amount= %7.2f%n", acctId, amount);
    }

}
