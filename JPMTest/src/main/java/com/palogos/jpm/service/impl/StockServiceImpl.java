package com.palogos.jpm.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidStockException;
import com.palogos.jpm.exceptions.InvalidStockIndexException;
import com.palogos.jpm.exceptions.StockNotFoundException;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.service.StockService;

@Service
public class StockServiceImpl implements StockService {

	private static Logger logger = Logger.getLogger("StockServiceImpl.class");

	@Autowired
	private AnnotationConfigApplicationContext annotationConfigApplicationContext;

	@Override
	public Stock calculateStockPrice(Stock stock) throws InvalidStockException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Double calculateShareIndex(String indexIdentifier) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void printIndex(String index) {
		ConcurrentHashMap<String, Stock> currentIndex = (ConcurrentHashMap<String, Stock>) annotationConfigApplicationContext
				.getBean(index);
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
}
