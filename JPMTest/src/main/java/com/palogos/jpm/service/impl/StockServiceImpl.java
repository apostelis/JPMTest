package com.palogos.jpm.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidStockException;
import com.palogos.jpm.exceptions.InvalidStockIndexException;
import com.palogos.jpm.exceptions.StockNotFoundException;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.model.Trade;
import com.palogos.jpm.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static Logger logger = Logger.getLogger("StockServiceImpl.class");

	@Autowired
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Autowired
	private LinkedList<Trade> tradeList;

	@Override
	public Stock calculateStockPrice(Stock stock) throws InvalidStockException {
		LinkedList<Trade> trades = new LinkedList<Trade>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -15);
		Date fifteenMinutesEarlier = cal.getTime();

		for (Trade trade : tradeList) {
			boolean isTradeForGivenStock = trade.getStock().getSymbol()
					.equalsIgnoreCase(stock.getSymbol());
			if (isTradeForGivenStock) {
				if (!trade.getTimestamp().before(fifteenMinutesEarlier)) {
					trades.add(trade);
				} else {
					break;
				}
			}
		}
		stock.setPrice(geometricMeanForListOfTrades(trades).doubleValue());
		return stock;
	}

	@Override
	public Double calculateShareIndex(String indexIdentifier)
			throws InvalidStockIndexException {
		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(indexIdentifier);
		if (currentIndex == null)
			throw new InvalidStockIndexException("Index not found");
		LinkedList<Trade> trades = new LinkedList<Trade>();
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MINUTE, -15);
		Date fifteenMinutesEarlier = cal.getTime();

		for (Trade trade : tradeList) {
			if (!trade.getTimestamp().before(fifteenMinutesEarlier)) {
				boolean isForGivenIndex = currentIndex.containsKey(trade
						.getStock().getSymbol().substring(0, 3));
				if (isForGivenIndex) {
					trades.add(trade);
				}
			} else {
				break;
			}
		}
		BigDecimal indexPrice = geometricMeanForListOfTrades(trades);
		logger.info(indexPrice);

		return indexPrice.doubleValue();
	}

	@Override
	public void printIndex(String indexIdentifier) {
		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(indexIdentifier);
		logger.info(currentIndex);
	}

	@Override
	public Stock getStockBySymbol(String stockSymbol)
			throws StockNotFoundException, InvalidStockIndexException,
			InvalidStockException {
		if (stockSymbol.indexOf('.') < 1) {
			throw new InvalidStockIndexException(
					"You must specify the index for the stock");
		}
		String[] symbolArray = stockSymbol.split("\\.");
		if (symbolArray.length < 2) {
			throw new InvalidStockException(
					"Invalid stock. Stocks must be specified by <symbol>.<index>");
		}
		String symbol = symbolArray[0];
		String index = symbolArray[1];
		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(index);
		if (currentIndex == null)
			throw new InvalidStockIndexException("Index not found");
		Stock currentStock = currentIndex.get(symbol);
		if (currentStock == null)
			throw new InvalidStockException("Stock symbol not found in index");

		return currentStock;
	}

	private BigDecimal geometricMeanForListOfTrades(LinkedList<Trade> trades) {
		BigDecimal nominator = new BigDecimal(0);
		BigDecimal denominator = new BigDecimal(0);
		for (Trade trade : trades) {
			BigDecimal quantity = new BigDecimal(trade.getQuantity());
			nominator.add(trade.getPrice().multiply(quantity));
			denominator.add(quantity);
		}
		if (denominator == null
				|| denominator.compareTo(new BigDecimal(0)) == 0) {
			return new BigDecimal(0);
		}
		BigDecimal geomMean = nominator.divide(denominator);
		return geomMean;
	}
}
