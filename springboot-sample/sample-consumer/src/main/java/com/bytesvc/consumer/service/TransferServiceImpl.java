package com.bytesvc.consumer.service;

import com.bytesvc.consumer.interfaces.ITransferService;
import lombok.extern.slf4j.Slf4j;
import org.bytesoft.compensable.Compensable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Compensable(interfaceClass = ITransferService.class, cancellableKey = "transferServiceCancel")
@Service("transferService")
public class TransferServiceImpl implements ITransferService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void transferAmount(String source, String target, double amount) {
        // 调用外部服务，进行扣钱
        this.restTemplate.postForEntity(String.format("http://127.0.0.1:3051/decrease/%s/%s", source, amount), null, Void.TYPE);
        // 更新本地账户
        this.jdbcTemplate.update("update tb_account_two set amount = amount + ? where identifier = ?", amount, target);
        log.debug("exec transfer: source= %s, target= %s, amount= %7.2f%n", source, target, amount);
    }

}
