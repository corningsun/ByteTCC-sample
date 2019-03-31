package com.bytesvc.provider.controller;

import com.bytesvc.provider.interfaces.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author corning
 */
@Slf4j
@RestController
public class ProviderController {

	@Autowired
	private IAccountService accountService;

	@ResponseBody
	@RequestMapping(value = "/increase/{acctId}/{amount}", method = RequestMethod.POST)
	public void increaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		accountService.increaseAmount(acctId, amount);
	}

	@ResponseBody
	@RequestMapping(value = "/decrease/{acctId}/{amount}", method = RequestMethod.POST)
	public void decreaseAmount(@PathVariable("acctId") String acctId, @PathVariable("amount") double amount) {
		accountService.decreaseAmount(acctId, amount);
	}

}
