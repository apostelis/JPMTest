package com.palogos.jpm.service;

import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Trade;

public interface TradeService {
	public boolean recordTrade(Trade trade) throws InvalidTradeException;
}
