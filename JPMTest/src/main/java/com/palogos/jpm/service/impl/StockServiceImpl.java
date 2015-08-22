package com.palogos.jpm.service.impl;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

import com.palogos.jpm.exceptions.InvalidStockException;
import com.palogos.jpm.model.Stock;
import com.palogos.jpm.service.StockService;

@Service
public class StockServiceImpl implements StockService {

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
		System.out.println(currentIndex);
	}
}
