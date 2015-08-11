package com.palogos.jpm.service;

import com.palogos.jpm.exceptions.InvalidStockException;
import com.palogos.jpm.model.Stock;

public interface StockService {

	public Stock calculateStockPrice(Stock stock) throws InvalidStockException;

	public Double calculateShareIndex(String indexIdentifier);
}
