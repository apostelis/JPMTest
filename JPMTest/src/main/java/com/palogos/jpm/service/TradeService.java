package com.palogos.jpm.service;

import java.math.BigDecimal;

import com.palogos.jpm.exceptions.InvalidIndexException;
import com.palogos.jpm.exceptions.InvalidTradeException;
import com.palogos.jpm.model.Demand;
import com.palogos.jpm.model.Offer;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;

public interface TradeService {
	public boolean recordTrade(Trade trade) throws InvalidTradeException;

	public void placeOffer(Offer offer);

	public void placeDemand(Demand demand);

	public Offer createOffer(String beneficiary, Stock stock, BigDecimal price,
			Integer quantity);

	public Demand createDemand(String beneficiary, Stock stock,
			BigDecimal price, Integer quantity);

	public int matchAndGenerateTrades(String index)
			throws InvalidIndexException;
}
