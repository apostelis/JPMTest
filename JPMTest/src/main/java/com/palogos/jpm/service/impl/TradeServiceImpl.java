package com.palogos.jpm.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Autowired
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Override
	public boolean recordTrade(Trade trade) throws InvalidTradeException {
		ConcurrentHashMap<String, Stock> gbce = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean("GBCE");
		if (gbce.containsKey(trade.getStock().getSymbol())) {

		} else {
			throw new InvalidTradeException(
					"Stock symbol does not exist in the index");
		}
		return false;
	}

}
