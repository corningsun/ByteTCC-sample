package com.bytesvc.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bytesvc.consumer.interfaces.ITransferService;

/**
 * @author corning
 */
@Slf4j
@Service("transferServiceCancel")
public class TransferServiceCancel implements ITransferService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void transferAmount(String source, String target, double amount) {
        this.jdbcTemplate.update("update tb_account_two set amount = amount - ? where identifier = ?", amount, target);
        log.debug("undo transfer: source= %s, target= %s, amount= %7.2f%n", source, target, amount);
    }

}
