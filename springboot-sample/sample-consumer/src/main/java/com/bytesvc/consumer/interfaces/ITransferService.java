package com.bytesvc.consumer.interfaces;

/**
 * @author corning
 */
public interface ITransferService {

    /**
     * 转账接口
     *
     * @param source 扣款账户
     * @param target 入款账户
     * @param amount 转账金额
     */
    void transferAmount(String source, String target, double amount);

}
