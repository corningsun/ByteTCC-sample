package com.bytesvc.consumer.controller;

import com.bytesvc.consumer.interfaces.ITransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author corning
 */
@RestController
public class ConsumerController {

    @Autowired
    private ITransferService transferService;

    @ResponseBody
    @PostMapping(value = "/transfer/{source}/{target}/{amount}")
    public void transferAmount(@PathVariable("source") String source,
                               @PathVariable("target") String target,
                               @PathVariable("amount") double amount) {
        transferService.transferAmount(source, target, amount);
    }

}
