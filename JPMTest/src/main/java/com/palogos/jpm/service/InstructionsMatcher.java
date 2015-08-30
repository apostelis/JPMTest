package com.palogos.jpm.service;

import java.util.UUID;

import com.palogos.jpm.exceptions.InvalidStockException;

public interface InstructionsMatcher {
	public void tradeSearch() throws InvalidStockException;

	public void matchDemand(UUID uuid);
}
