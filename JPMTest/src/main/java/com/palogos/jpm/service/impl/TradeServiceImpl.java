package com.palogos.jpm.service.impl;

import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.TradeService;

@Service
public class TradeServiceImpl implements TradeService {

	@Override
	public boolean recordTrade(Trade trade) throws InvalidTradeException {
		// TODO Auto-generated method stub
		return false;
	}

}
