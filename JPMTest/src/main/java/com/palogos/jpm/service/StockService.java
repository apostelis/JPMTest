package com.palogos.jpm.service;

import com.palogos.jpm.exceptions.InvalidStockException;
import com.palogos.jpm.exceptions.InvalidStockIndexException;
import com.palogos.jpm.exceptions.StockNotFoundException;
import com.palogos.jpm.model.Stock;

public interface StockService {

	public void updateStockPrice(Stock stock) throws InvalidStockException;

	public Double calculateShareIndex(String indexIdentifier)
			throws InvalidStockIndexException;

	public void printIndex(String string);

	public Stock getStockBySymbol(String string) throws StockNotFoundException,
			InvalidStockIndexException, InvalidStockException;
}
