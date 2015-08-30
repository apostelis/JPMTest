package com.palogos.jpm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.palogos.jpm.exceptions.InvalidStockIndexException;
import com.palogos.jpm.service.StockService;

@Component
public class SchedulerImpl {
	private static Logger logger = Logger.getLogger(SchedulerImpl.class);

	@Autowired
	StockService stockService;

	@Scheduled(fixedDelay = 15000)
	public void calculateTheGBCEShareIndex() {
		try {
			stockService.calculateShareIndex("gbce");
		} catch (InvalidStockIndexException e) {
			logger.error(
					"Error in the calculation of the gbce share index price ",
					e);
		}
	}
}
