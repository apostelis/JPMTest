package com.palogos.jpm.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.annotation.Async;
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
	@Async
	public void updateStockPrice(Stock stock) throws InvalidStockException {
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
		stock.setPrice(calculateStockPriceBasedOnListOfTrades(trades)
				.doubleValue());
		logger.info(stock);

		String[] symbolArray;
		try {
			symbolArray = splitIndexFromSymbol(stock.getSymbol());
			String indexIdentifier = symbolArray[1];
			// String symbol = symbolArray[0];
			ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
					.getBean(indexIdentifier);
			if (currentIndex == null)
				throw new InvalidStockIndexException("Index not found");

			currentIndex.put(stock.getSymbol(), stock);

		} catch (InvalidStockIndexException e) {
			logger.error("Error in stock price calculation", e);
		}

	}

	@Override
	public Double calculateShareIndex(String indexIdentifier)
			throws InvalidStockIndexException {
		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(indexIdentifier);
		if (currentIndex == null)
			throw new InvalidStockIndexException("Index not found");
		BigDecimal priceMult = new BigDecimal(0);

		ArrayList<String> keysList = new ArrayList<String>(5);
		keysList.addAll(currentIndex.keySet());
		for (int i = 0; i < keysList.size(); i++) {
			if (i == 0) {
				priceMult = new BigDecimal(currentIndex.get(keysList.get(i))
						.getPrice());
			} else {
				priceMult.multiply(new BigDecimal(currentIndex.get(
						keysList.get(i)).getPrice()));
			}
		}

		double indexPrice = Math.pow(priceMult.doubleValue(),
				1.0 / keysList.size());

		logger.info(indexIdentifier + " new price: " + indexPrice);

		return indexPrice;
	}

	private BigDecimal geometricMeanForListOfTrades(LinkedList<Trade> trades) {
		BigDecimal retval = new BigDecimal(0);

		return retval;
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

		String[] symbolArray = splitIndexFromSymbol(stockSymbol);

		String index = symbolArray[1];

		String symbol = symbolArray[0];

		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(index);
		if (currentIndex == null)
			throw new InvalidStockIndexException("Index not found");
		Stock currentStock = currentIndex.get(symbol);
		if (currentStock == null)
			throw new InvalidStockException("Stock symbol not found in index");

		return currentStock;
	}

	private BigDecimal calculateStockPriceBasedOnListOfTrades(
			LinkedList<Trade> trades) {
		BigDecimal nominator = new BigDecimal(0);
		BigDecimal denominator = new BigDecimal(0);
		for (Trade trade : trades) {
			BigDecimal quantity = new BigDecimal(trade.getQuantity());
			nominator = nominator.add(trade.getPrice().multiply(quantity));
			denominator = denominator.add(quantity);
		}
		if (denominator == null
				|| denominator.compareTo(new BigDecimal(0)) == 0) {
			return new BigDecimal(0);
		}
		BigDecimal movingWinPriceMean = nominator.divide(denominator);
		return movingWinPriceMean;
	}

	private String[] splitIndexFromSymbol(String stockSymbol)
			throws InvalidStockIndexException, InvalidStockException {
		if (stockSymbol.indexOf('.') < 1) {
			throw new InvalidStockIndexException(
					"You must specify the index for the stock");
		}
		String[] symbolArray = stockSymbol.split("\\.");
		if (symbolArray.length < 2) {
			throw new InvalidStockException(
					"Invalid stock. Stocks must be specified by <symbol>.<index>");
		}
		return symbolArray;
	}
}
